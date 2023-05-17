package com.example.cpaweb.services

import com.example.cpaweb.models.users.CreateRatingRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface RatingService {
    @Headers("Content-Type: application/json")
    @POST("/professional/:professionalId/rating")
    fun createProfessionalRating(@Path("id") professionalId: Long, @Body createProfessionalRequest: CreateRatingRequest): Call<Void>
}