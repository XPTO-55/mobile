package com.example.cpaweb.services

import com.example.cpaweb.models.users.professionals.CreateProfessionalRequest
import com.example.cpaweb.models.users.professionals.Professional
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ProfessionalService {
    @Headers("Content-Type: application/json")
    @POST("/professionals")
    fun createProfessional(@Body createProfessionalRequest: CreateProfessionalRequest): Call<Void>

    @GET("/professionals")
    fun getProfessionals(): Call<List<Professional>>
}