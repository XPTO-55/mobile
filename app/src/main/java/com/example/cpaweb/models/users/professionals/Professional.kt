package com.example.cpaweb.models.users.professionals

import com.example.cpaweb.models.users.UserBase
import java.time.LocalDate

data class Professional (
    val especialidade: String? = null
): UserBase()
