package com.example.cpaweb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.EditText

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun login(componente: View) {
        val login = Intent(applicationContext, Login::class.java)
        startActivity(login)
    }

    fun cadastro(componente: View) {

        val loginChoice = Intent(applicationContext, LoginChoice::class.java)

        startActivity(loginChoice)
    }
}