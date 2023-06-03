package com.example.cpaweb.models.auth

data class LoginResponse(
    val jwtToken: String = "",
    val type: String = "Bearer",
    val userType: String = "" ,
    val refreshToken: String = "",
    val username: String = "",
    val email: String = "",
    val profileUrl: String? = "",
    val id: Long = 0L,
    val phone: String = "",
)
