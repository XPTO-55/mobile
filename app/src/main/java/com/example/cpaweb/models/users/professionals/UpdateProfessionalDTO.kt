package com.example.cpaweb.models.users.professionals

import DateHelper
import com.example.cpaweb.models.Address
import com.google.gson.annotations.JsonAdapter
import java.time.LocalDate

data class UpdateProfessionalDTO(
    val name: String = "",
    val email: String = "",
    @JsonAdapter(DateHelper::class)
    val birthday: LocalDate? = null,
    val phone: String? = null,
    val cpf: String? = null,
    val password: String = "",
    val rg: String? = "",
    val about: String? = "",
    val landline: String? = "",
    val address: Address?,
    val identificacao: String? = null,
    val especialidade: String? = null,
    val graduacao: String? = null,
)
