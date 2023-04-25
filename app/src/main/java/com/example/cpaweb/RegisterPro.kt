package com.example.cpaweb

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.cpaweb.models.users.patient.CreatePatientRequest
import com.example.cpaweb.services.Api
import com.example.cpaweb.services.PatientService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class RegisterPro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pro_register)
    }

    fun telaInicial(componente: View) {

        val main = Intent(applicationContext, MainActivity::class.java)

        startActivity(main)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun registerSucess(componente: View) {

        val registerSucess = Intent(applicationContext, RegisterSucess::class.java)

        val name = findViewById<EditText>(R.id.edt_name)
        val email = findViewById<EditText>(R.id.edt_email)
        val birthday = findViewById<EditText>(R.id.edt_birthday)
        val phone = findViewById<EditText>(R.id.edt_phone)
        val cpf = findViewById<EditText>(R.id.edt_cpf)
        val password = findViewById<EditText>(R.id.edt_password)
        val confirmPassword = findViewById<EditText>(R.id.edt_confirm_password)

        if(!password.text.toString().equals(confirmPassword.text.toString())){
            findViewById<EditText>(R.id.edt_confirm_password).error = "As senhas não conferem"
            return
        }

        val pattern = "dd/MM/yyyy" // padrão de formato de data
        val formatter = DateTimeFormatter.ofPattern(pattern) // cria um objeto DateTimeFormatter com o padrão de formato

        val dateString = birthday.text.toString() // ob
        val selectedBirthdayDate = if (dateString.isNotEmpty()) {
            LocalDate.parse(dateString, formatter) // converte o texto em um objeto LocalDate usando o padrão de formato
        } else {
            null
        }

        val service = Api.createService(PatientService::class.java)
        val createPatientRequest = CreatePatientRequest(
            name.text.toString(),
            email.text.toString(),
            selectedBirthdayDate,
            phone.text.toString(),
            cpf.text.toString(),
            password.text.toString()
        )
        val request: Call<Void> = service.createPatient(createPatientRequest)
        request.enqueue(object: Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if(response.isSuccessful){
                    startActivity(registerSucess)
                } else if(response.code() == 401){
                    Toast.makeText(applicationContext, "Houve um erro ao criar o profissional", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(applicationContext, "Ops! houve um erro na conexão", Toast.LENGTH_SHORT).show()
            }
        })
    }
}