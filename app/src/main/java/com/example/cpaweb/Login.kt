package com.example.cpaweb

import LoginCallback
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.example.cpaweb.databinding.ActivityLoginBinding
import com.example.cpaweb.helpers.AuthManager
import com.example.cpaweb.models.auth.LoginRequest
import com.example.cpaweb.models.auth.LoginResponse
import com.example.cpaweb.models.users.UserBase
import com.example.cpaweb.rest.Api
import com.example.cpaweb.services.AuthService
import retrofit2.Call

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AuthManager.init(this)

        binding.btnLogin.setOnClickListener {
            tryLogin()
        }

        binding.txtRegister.setOnClickListener {
            startActivity(Intent(baseContext, LoginChoice::class.java))
        }
    }

    fun tryLogin() {
        val email = findViewById<EditText>(R.id.edt_email).text.toString()
        val password = findViewById<EditText>(R.id.edt_password).text.toString()

        val service = Api.createService(AuthService::class.java)
        val loginRequest = LoginRequest(email, password)
        val request: Call<LoginResponse> = service.login(loginRequest)

        request.enqueue(LoginCallback(
            ::saveToken,
            ::saveUserInfo,
            { mensagem ->
                Toast.makeText(
                    baseContext,
                    mensagem,
                    Toast.LENGTH_LONG
                ).show()
            },
            ::launchActivity,
            ::finishActivity
        ))
    }

    private fun launchActivity() {
        startActivity(Intent(baseContext, CommunityHome::class.java))
    }

    private fun finishActivity() {
        finishActivity()
    }

    private fun saveToken(token: String){
        AuthManager.saveAuthToken(token)
    }

    private fun saveUserInfo(userData: LoginResponse){
        AuthManager.saveUser(userData)
    }
}