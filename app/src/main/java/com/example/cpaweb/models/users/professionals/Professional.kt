package com.example.cpaweb.models.users.professionals

import com.example.cpaweb.models.users.UserBase
import java.time.LocalDate

data class Professional (
    override val id: Long = 0L,
    override val name: String,
    override val email: String = "",
    override val password: String = "",
    override val profileUrl: String? = null,
    override val cpf: String? = null,
    override val about: String? = null,
    override val birthday: LocalDate? = null,
    override val landline: String? = null,
    override val phone: String? = null,
    val especialidade: String? = null
): UserBase(id,name, email, password, profileUrl, cpf, about, birthday, landline, phone)
