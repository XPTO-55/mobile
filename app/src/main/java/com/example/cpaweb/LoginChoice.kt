package com.example.cpaweb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class LoginChoice : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_choice)
    }

    fun cadastroProfissional(componente: View) {

        val cadastro = Intent(applicationContext, RegisterPro::class.java)

        startActivity(cadastro)
    }

    fun cadastroComum(componente: View) {

        val cadastro = Intent(applicationContext, RegisterCommon::class.java)

        startActivity(cadastro)
    }

}