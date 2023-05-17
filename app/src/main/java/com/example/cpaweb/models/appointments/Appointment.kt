package com.example.cpaweb.models.appointments

import java.time.LocalDate
import java.time.LocalDateTime

data class Appointment(
    val patient: String,
    val professional: String,
    val professionalId: Long,    
    val especiality: String,
    val date: LocalDateTime
)
