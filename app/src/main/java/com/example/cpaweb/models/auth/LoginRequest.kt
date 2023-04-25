package com.example.cpaweb.models.auth

data class LoginRequest(
    val email: String = "",
    val password: String = ""
)
