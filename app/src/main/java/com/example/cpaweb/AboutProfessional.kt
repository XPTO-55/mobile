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
    private var mService: Calendar? = null
    private val RC_SIGN_IN = 123

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutProfessionalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        professional = intent.getSerializableExtra("professionalData") as Professional
        populateFields()
        bindings()
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
    private inner class CreateEventTask : AsyncTask<Void, Void, Unit>() {
        override fun doInBackground(vararg params: Void?): Unit {
            return createEvent()
        }

        override fun onPostExecute(result: Unit?) {
            runOnUiThread {
                Toast.makeText(baseContext, "Event created", Toast.LENGTH_SHORT).show()
            }
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

    private fun bindings(){
        configureGoogleSignIn()
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

    fun generateMeetingName(): String {
        val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
        val length = 10
        return (1..length)
            .map { chars.random() }
            .joinToString("")
    }

    private fun createConferenceRequest(): CreateConferenceRequest? {
        val conferenceRequest = CreateConferenceRequest()

        // Configurar a chave da solução da conferência (neste caso, o Google Meet)
        val solutionKey = ConferenceSolutionKey()
        solutionKey.type = "hangoutsMeet" // Definir o tipo como "hangoutsMeet"
        conferenceRequest.conferenceSolutionKey = solutionKey
        return conferenceRequest
    }

    private fun createConferenceData(): ConferenceData? {
        val conferenceData = ConferenceData()
        // Configurar os detalhes da conferência
        conferenceData.entryPoints = listOf<EntryPoint>(
            EntryPoint().setEntryPointType("video")
        )
        val conferenceParameters = ConferenceParameters()
        conferenceData.parameters = conferenceParameters
        conferenceData.createRequest = createConferenceRequest()
        return conferenceData
    }

    private fun createEvent() {
        try {
            // Gerar um ID único aleatório
            var requestId = UUID.randomUUID().toString()

            // Gerar o link do Meet com base no ID aleatório
            val meetLink = "https://meet.google.com/$requestId"

            var event: Event = Event()
                .setSummary("Consulta Psiquiatrica")
                .setDescription("Avaliação")
            val mCalendar = GregorianCalendar()

            val timeZone = SimpleDateFormat("Z", Date().getCurrentLocale()).format(mCalendar.time)

            val start = EventDateTime()
                .setDateTime(DateTime("2023-06-04T10:00:00"))
                .setTimeZone(timeZone)

            event.start = start

            val end = EventDateTime()
                .setDateTime(DateTime("2023-06-04T11:00:00"))
                .setTimeZone(timeZone)
            event.end = end

            // professional.email
            event.attendees = listOf(EventAttendee().setEmail("lukasalves271@gmail.com"))

//            val conference = ConferenceData()
//
//            val conferenceSolution = ConferenceSolution()
//                .setKey(ConferenceSolutionKey().setType("hangoutsMeet"))
//            conference.conferenceSolution = conferenceSolution
//
//            val createRequest = CreateConferenceRequest()
//                .setRequestId(generateMeetingName())
//                .setConferenceSolutionKey(ConferenceSolutionKey().setType("hangoutsMeet"))
//            conference.createRequest = createRequest

            event.conferenceData = createConferenceData()

            val calendarId = "primary"

            mService?.events()?.insert(calendarId, event)?.execute()?.let {
                event = it
                val conferenceData = event.conferenceData
                val meetLink = conferenceData?.entryPoints?.find { it.entryPointType == "video" }?.uri
            }
        } catch (e: Exception) {
            e.printStackTrace()
            println("EVENT ERROR: ${e.message}")
            Toast.makeText(binding.root.context, "Failed to create event", Toast.LENGTH_SHORT)
                .show()
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
}