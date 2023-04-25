package com.example.cpaweb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class RegisterSucess : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_sucess)
    }

    fun login(componente: View) {

        val login = Intent(applicationContext, Login::class.java)

        startActivity(login)
    }
}