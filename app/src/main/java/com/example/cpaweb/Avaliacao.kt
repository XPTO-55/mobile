package com.example.cpaweb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.FragmentContainerView
import com.example.cpaweb.fragments.FragmentAvaliacaoDialog
import com.example.cpaweb.models.AvaliacaoFeedback

class Avaliacao : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_avaliacao)
        findViewById<Button>(R.id.btnAbrirDialog).setOnClickListener {
            abrirDialog()
        }
    }

    private fun enviarFeedback(avaliacao: AvaliacaoFeedback) {
        // Pegar o avaliacao e enviar para o back-end
    }


    private fun abrirDialog() {
        FragmentAvaliacaoDialog(::enviarFeedback)
            .show(supportFragmentManager, "DIALOG_AVALIACAO")
    }
}