package com.example.cpaweb.services

import com.example.cpaweb.models.users.UserBase
import com.example.cpaweb.models.users.patient.CreatePatientRequest
import com.example.cpaweb.models.users.patient.UpdatePatientDTO
import com.example.cpaweb.models.users.professionals.Professional
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PatientService {
    @GET("/patients/{patientId}")
    fun getPatient(@Path("patientId") patientId: Long): Call<UserBase>

    @Headers("Content-Type: application/json")
    @POST("/patients")
    fun createPatient(@Body createPatientRequest: CreatePatientRequest): Call<Void>

    @Headers("Content-Type: application/json")
    @PUT("/patients")
    fun updatePatient(@Body updatePatientRequest: UpdatePatientDTO): Call<Void>
}