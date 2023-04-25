package com.example.cpaweb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.cpaweb.models.auth.LoginRequest
import com.example.cpaweb.models.auth.LoginResponse
import com.example.cpaweb.services.Api
import com.example.cpaweb.services.AuthService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun telaHome(component: View) {
        val telaHome = Intent(applicationContext, communityHome::class.java)
        val email = findViewById<EditText>(R.id.edt_email).text.toString()
        val password = findViewById<EditText>(R.id.edt_password).text.toString()

        val service = Api.createService(AuthService::class.java)
        val loginRequest = LoginRequest(email, password)
        val request: Call<LoginResponse> = service.login(loginRequest)

        request.enqueue(object: Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if(response.isSuccessful){
                    startActivity(telaHome)
                }else if(response.code() === 401){
                    Toast.makeText(applicationContext, "Credenciais inválidas", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "Ops! houve um erro na conexão", Toast.LENGTH_SHORT).show()
            }
        })
    }
}