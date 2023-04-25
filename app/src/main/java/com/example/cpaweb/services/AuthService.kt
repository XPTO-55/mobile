package com.example.cpaweb.services

import com.example.cpaweb.models.auth.LoginRequest
import com.example.cpaweb.models.auth.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthService {
    @Headers("Content-Type: application/json")
    @POST("/auth/login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>
}