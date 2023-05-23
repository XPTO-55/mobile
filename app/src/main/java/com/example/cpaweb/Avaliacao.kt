package com.example.cpaweb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.cpaweb.fragments.FragmentAvaliacaoDialog
import com.example.cpaweb.models.users.CreateRatingRequest
import com.example.cpaweb.rest.Api
import com.example.cpaweb.services.RatingService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Avaliacao : AppCompatActivity() {
    val dialog: FragmentAvaliacaoDialog = FragmentAvaliacaoDialog(::enviarFeedback)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_avaliacao)
        findViewById<Button>(R.id.btnAbrirDialog).setOnClickListener {
            abrirDialog()
        }
    }

    private fun enviarFeedback(professionalId: Long, comment: String, rating: Double) {
        // Pegar o avaliacao e enviar para o back-end
        val service = Api.createService(RatingService::class.java)
        val createRatingRequest = CreateRatingRequest(comment, rating)
        val request: Call<Void> = service.createProfessionalRating(professionalId, createRatingRequest)

        request.enqueue(object: Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if(response.isSuccessful){
                    Toast.makeText(applicationContext, "Obrigado pelo seu feedback!", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }else if(response.code() >= 400){
                    Toast.makeText(applicationContext, "Tivemos um problema com seu feedback, poderia enviar novamente?", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(applicationContext, "Ops! houve um erro na conexão", Toast.LENGTH_SHORT).show()
            }
        })
    }


    private fun abrirDialog() {
        dialog.show(supportFragmentManager, "DIALOG_AVALIACAO")
    }
}