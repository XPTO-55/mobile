package com.example.cpaweb.models.users.patient

import java.time.LocalDate

data class CreatePatientResponse(
    val id: Long = 0L,
    val name: String = "",
    val email: String = "",
    val birthday: LocalDate? = null,
    val phone: String = "",
    val password: String = "",
)
