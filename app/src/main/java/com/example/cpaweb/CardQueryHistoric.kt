package com.example.cpaweb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.cpaweb.fragments.FragmentAvaliacaoDialog
import com.example.cpaweb.models.users.CreateRatingRequest
import com.example.cpaweb.rest.Api
import com.example.cpaweb.services.RatingService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CardQueryHistoric : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_query_historic)
    }
}