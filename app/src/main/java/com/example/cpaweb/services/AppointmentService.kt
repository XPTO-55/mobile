package com.example.cpaweb.services

import com.example.cpaweb.models.appointments.Appointment
import com.example.cpaweb.models.users.CreateRatingRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface AppointmentService {
    @Headers("Content-Type: application/json")
    @GET("/appointments/{userId}")
    fun getAppointments(@Path("userId") userId: Long): Call<List<Appointment>>
}