package com.example.cpaweb.models.users

import java.time.LocalDate
import java.io.Serializable

open class UserBase (
  open val id: Long = 0L,
  open val name: String = "",
  open val email: String = "",
  open val password: String = "",
  open val profileUrl: String? = null,
  open val cpf: String? = null,
  open val about: String? = null,
  open val birthday: LocalDate? = null,
  open val landline: String? = null,
  open val phone: String? = null,
) : Serializable