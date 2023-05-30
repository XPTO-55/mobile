package com.example.cpaweb.models.users.professionals

import com.example.cpaweb.models.users.UserBase
import java.time.LocalDate

data class Professional (
    val id: Long = 0L,
    val name: String,
    val email: String = "",
    val password: String = "",
    val profileUrl: String? = null,
    val cpf: String? = null,
    val about: String? = null,
    val birthday: LocalDate? = null,
    val landline: String? = null,
    val phone: String? = null,
    val especialidade: String? = null
)
