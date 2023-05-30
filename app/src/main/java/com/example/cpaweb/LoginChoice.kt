package com.example.cpaweb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.cpaweb.databinding.ActivityLoginChoiceBinding

class LoginChoice : AppCompatActivity() {
    private lateinit var binding: ActivityLoginChoiceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginChoiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtLogin.setOnClickListener {
            startActivity(Intent(baseContext, Login::class.java))
        }

        binding.btnRegisterCommon.setOnClickListener {
            cadastroComum()
        }

        binding.btnRegisterProfessional.setOnClickListener {
            cadastroProfissional()
        }
    }

    fun cadastroProfissional() {
        val cadastro = Intent(applicationContext, RegisterPro::class.java)
        startActivity(cadastro)
    }

    fun cadastroComum() {
        val cadastro = Intent(applicationContext, RegisterCommon::class.java)
        startActivity(cadastro)
    }

}