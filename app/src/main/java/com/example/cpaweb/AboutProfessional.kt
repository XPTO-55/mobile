package com.example.cpaweb

import android.content.Intent
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cpaweb.databinding.ActivityAboutProfessionalBinding
import com.example.cpaweb.helpers.Constants
import com.example.cpaweb.models.users.professionals.Professional
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.Scope
import com.google.android.gms.tasks.Task
import com.google.api.client.extensions.android.http.AndroidHttp
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.client.util.DateTime
import com.google.api.services.calendar.Calendar
import com.google.api.services.calendar.CalendarScopes
import com.google.api.services.calendar.model.ConferenceData
import com.google.api.services.calendar.model.ConferenceParameters
import com.google.api.services.calendar.model.ConferenceSolution
import com.google.api.services.calendar.model.ConferenceSolutionKey
import com.google.api.services.calendar.model.CreateConferenceRequest
import com.google.api.services.calendar.model.EntryPoint
import com.google.api.services.calendar.model.Event
import com.google.api.services.calendar.model.EventAttendee
import com.google.api.services.calendar.model.EventDateTime
import java.text.SimpleDateFormat
import java.util.Date
import java.util.GregorianCalendar
import java.util.Locale
import java.util.UUID


class AboutProfessional : AppCompatActivity() {
    private lateinit var binding: ActivityAboutProfessionalBinding
    private lateinit var professional: Professional
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutProfessionalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        professional = intent.getSerializableExtra("professionalData") as Professional
        populateFields()
        bindings()
    }

    private fun bindings() {
        binding.btnSendAppointment.setOnClickListener {
            val intent = Intent(this, ScheduleAppointment::class.java)
            intent.putExtra("professionalData", professional)
            startActivity(intent)
        }
    }

    private fun populateFields(){
//        if(professional.profileUrl != ""){
//            binding.ciProfileImage.setImageURI(Uri.parse(professional.profileUrl))
//        }
        binding.tvProfessionalNameText.text = professional.name.toString()
        binding.tvProfessionalSpecialityText.text = professional.especialidade.toString()
        binding.rbRating.rating = professional.ratings.map { it.rating }.average().toFloat()
        val about = professional.about?.toString()
        if(about != null){
            binding.tvProfessionalAboutText.text = about
        }else {
            binding.tvProfessionalAboutText.text = ""
        }
        binding.tvProfessionalEmailText.text = professional.email.toString()
        val address = professional.address?.street
        if(address != null){
            binding.tvProfessionalAddressText.text = address.toString()
        }else {
            binding.tvProfessionalAddressText.text = ""
        }

        binding.tvProfessionalPhoneText.text = professional.phone.toString()
    }
}