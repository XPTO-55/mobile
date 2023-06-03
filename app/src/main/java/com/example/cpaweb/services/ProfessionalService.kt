package com.example.cpaweb.services

import com.example.cpaweb.models.users.patient.UpdatePatientDTO
import com.example.cpaweb.models.users.professionals.CreateProfessionalRequest
import com.example.cpaweb.models.users.professionals.Professional
import com.example.cpaweb.models.users.professionals.UpdateProfessionalDTO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ProfessionalService {
    @Headers("Content-Type: application/json")
    @POST("/professionals")
    fun createProfessional(@Body createProfessionalRequest: CreateProfessionalRequest): Call<Void>

    @GET("/professionals")
    fun getProfessionals(): Call<List<Professional>>

    @GET("/professionals/{professionalId}")
    fun getProfessional(@Path("professionalId") professionalId: Long): Call<Professional>

    @Headers("Content-Type: application/json")
    @PUT("/professionals")
    fun updateProfessional(@Body updateProfessionalRequest: UpdateProfessionalDTO): Call<Void>
}