package com.example.cpaweb.services

import com.example.cpaweb.models.users.professionals.CreateProfessionalRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ProfesssionalService {
    @Headers("Content-Type: application/json")
    @POST("/professionals")
    fun createPatient(@Body createProfessionalRequest: CreateProfessionalRequest): Call<Void>
}