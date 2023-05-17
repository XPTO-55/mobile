package com.example.cpaweb.models.users

import java.time.LocalDate
import java.io.Serializable

open class UserBase (
  val id: Long = 0L,
  val name: String = "",
  val email: String = "",
  val password: String = "",
  val profileUrl: String? = null,
  val cpf: String? = null,
  val about: String? = null,
  val birthday: LocalDate? = null,
  val landline: String? = null,
  val phone: String? = null,
) : Serializable