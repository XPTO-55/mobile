package com.example.cpaweb

import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.cpaweb.databinding.ActivityAboutProfessionalBinding
import com.example.cpaweb.databinding.ActivityScheduleAppointmentBinding
import com.example.cpaweb.helpers.AuthManager
import com.example.cpaweb.helpers.Constants
import com.example.cpaweb.models.appointments.AppointmentData
import com.example.cpaweb.models.appointments.CreateAppointmentRequestDTO
import com.example.cpaweb.models.users.professionals.Professional
import com.example.cpaweb.rest.Api
import com.example.cpaweb.services.AppointmentService
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.Scope
import com.google.android.gms.tasks.Task
import com.google.android.material.timepicker.TimeFormat
import com.google.api.client.extensions.android.http.AndroidHttp
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.client.util.DateTime
import com.google.api.services.calendar.Calendar
import com.google.api.services.calendar.CalendarScopes
import com.google.api.services.calendar.model.ConferenceData
import com.google.api.services.calendar.model.ConferenceParameters
import com.google.api.services.calendar.model.ConferenceSolutionKey
import com.google.api.services.calendar.model.CreateConferenceRequest
import com.google.api.services.calendar.model.EntryPoint
import com.google.api.services.calendar.model.Event
import com.google.api.services.calendar.model.EventAttendee
import com.google.api.services.calendar.model.EventDateTime
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.Date
import java.util.GregorianCalendar
import java.util.Locale

class ScheduleAppointment : AppCompatActivity() {
    private lateinit var binding: ActivityScheduleAppointmentBinding
    private lateinit var professional: Professional
    private var mService: Calendar? = null
    private val RC_SIGN_IN = 123
    private val service = Api.createService<AppointmentService>(AppointmentService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule_appointment)
        AuthManager.init(this)
        binding = ActivityScheduleAppointmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        professional = intent.getSerializableExtra("professionalData") as Professional
        populateFields()
        configureGoogleSignIn()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleGoogleSignInResult(task)
        }
    }

    private fun handleGoogleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            val credential = GoogleAccountCredential.usingOAuth2(
                applicationContext, listOf(CalendarScopes.CALENDAR)
            )
            credential.selectedAccount = account?.account
            Toast.makeText(this, "Calendário conectado com sucesso", Toast.LENGTH_SHORT).show()
            initializeCalendarService(credential)
        } catch (e: ApiException) {
            // O login com o Google falhou
            Log.e("ERROR", "Google sign-in failed", e)
        }
    }

    private fun initializeCalendarService(credential: GoogleAccountCredential) {
        val transport = AndroidHttp.newCompatibleTransport()
        val jsonFactory = JacksonFactory.getDefaultInstance()
        mService = Calendar.Builder(transport, jsonFactory, credential)
            .setApplicationName(Constants.APPLICATION_NAME)
            .build()
        CreateEventTask().execute()

    }
    private inner class CreateEventTask : AsyncTask<Void, Void, Event>() {
        override fun doInBackground(vararg params: Void?): Event {
            return createEvent()
        }

        override fun onPostExecute(result: Event) {
            runOnUiThread {
                Toast.makeText(baseContext, "Event created", Toast.LENGTH_SHORT).show()
                populateAppointmentData(result)
                service.createAppointment(CreateAppointmentRequestDTO(
                    patientId = AuthManager.getUserInfoId(),
                    profissionalId = professional?.id
                )).enqueue(object: Callback<Unit> {
                    override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                        if(!response.isSuccessful){
                            Toast.makeText(baseContext, "Erro ao criar consulta", Toast.LENGTH_SHORT).show()
                           println("EVENT ERROR ${response.errorBody().toString()}")
                        }
                    }

                    override fun onFailure(call: Call<Unit>, t: Throwable) {
                        Toast.makeText(baseContext, "Ops! Houve um erro de conexão", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }
    }

    private fun populateAppointmentData(event: Event) {
        binding.containerEventResult.visibility = View.VISIBLE
        binding.tbEventLinkText.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(event.htmlLink))
            startActivity(intent)
        }
    }

    private fun configureGoogleSignIn() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestScopes(Scope(CalendarScopes.CALENDAR))
            .build()

        val googleSignInClient = GoogleSignIn.getClient(this, gso)

        binding.btnSendAppointment.setOnClickListener {
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }
    }

    private fun createConferenceRequest(): CreateConferenceRequest? {
        val conferenceRequest = CreateConferenceRequest()
        val solutionKey = ConferenceSolutionKey()
        solutionKey.type = "hangoutsMeet"
        conferenceRequest.conferenceSolutionKey = solutionKey
        return conferenceRequest
    }

    private fun createConferenceData(): ConferenceData? {
        val conferenceData = ConferenceData()
        conferenceData.entryPoints = listOf<EntryPoint>(
            EntryPoint().setEntryPointType("video")
        )
        val conferenceParameters = ConferenceParameters()
        conferenceData.parameters = conferenceParameters
        conferenceData.createRequest = createConferenceRequest()
        return conferenceData
    }

    private fun createEvent(): Event {
        try {
            var event: Event = Event()
                .setSummary(String.format("Consulta %s", professional.especialidade))
                .setDescription("Avaliação")
            val mCalendar = GregorianCalendar()

            val timeZone = SimpleDateFormat("Z", Date().getCurrentLocale()).format(mCalendar.time)

            val start = EventDateTime()
                .setDateTime(DateTime(Date().apply {
                    date = date + 1
                    hours = 21
                    minutes = 0
                }))
                .setTimeZone(timeZone)

            event.start = start

            val end = EventDateTime()
                .setDateTime(DateTime(Date().apply {
                    date = date + 1
                    hours = 21 + 1
                    minutes = 0
                }))
                .setTimeZone(timeZone)
            event.end = end

            intent.getStringExtra("professionalEmail")
            event.attendees = listOf(EventAttendee().setEmail(professional.email))

            event.conferenceData = createConferenceData()

            val calendarId = "primary"

            mService?.events()?.insert(calendarId, event)?.execute()?.let {
                event = it
                val conferenceData = event.conferenceData
                val meetLink = conferenceData?.entryPoints?.find { it.entryPointType == "video" }?.uri
            }

            return event
        } catch (e: Exception) {
            e.printStackTrace()
            println("EVENT ERROR: ${e.message}")
            Toast.makeText(binding.root.context, "Failed to create event", Toast.LENGTH_SHORT)
                .show()
            return Event()
        }
    }

    private fun Date.convertToFullDateFormat(): String {
        val dateFormatter =
            SimpleDateFormat(
                binding.root.context.getString(
                    R.string.date_format_patterns
                ), this.getCurrentLocale()
            )
        return dateFormatter.format(this)
    }
    private fun Date.getCurrentLocale() =
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            resources.configuration.locales.get(0)
        }else{
            @Suppress("DEPRECATION")
            resources.configuration.locale
        }

    fun convertToEventDateTime(dateString: String): String {
        val inputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val outputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val date: Date? = inputFormat.parse(dateString)
        return outputFormat.format(date)
    }

    private fun populateFields() {
        binding.tbPatientNameText.text = AuthManager.getUser().username
        binding.tbProfessionalNameText.text = professional.name
    }

}