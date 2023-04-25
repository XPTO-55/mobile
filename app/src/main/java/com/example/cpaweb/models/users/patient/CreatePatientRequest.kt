package com.example.cpaweb.models.users.patient

import java.time.LocalDate

data class CreatePatientRequest(
    val name: String = "",
    val email: String = "",
    val birthday: LocalDate? = null,
    val phone: String? = null,
    val cpf: String? = null,
    val password: String = "",
)
