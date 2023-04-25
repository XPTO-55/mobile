package com.example.cpaweb.services

import com.example.cpaweb.models.users.patient.CreatePatientRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface PatientService {
    @Headers("Content-Type: application/json")
    @POST("/patients")
    fun createPatient(@Body createPatientRequest: CreatePatientRequest): Call<Void>
}