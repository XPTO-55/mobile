package com.example.cpaweb.models.users.patient

import com.example.cpaweb.models.Address
import java.time.LocalDate

data class UpdatePatientDTO(
    val name: String = "",
    val email: String = "",
    val birthday: LocalDate? = null,
    val phone: String? = null,
    val cpf: String? = null,
    val password: String = "",
    val rg: String? = "",
    val about: String? = "",
    val landline: String? = "",
    val address: Address?,
)
