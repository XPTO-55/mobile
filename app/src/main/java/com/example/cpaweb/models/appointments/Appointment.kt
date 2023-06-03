package com.example.cpaweb.models.appointments

import DateHelper
import DateTimeHelper
import com.google.gson.annotations.JsonAdapter
import java.time.LocalDate
import java.time.LocalDateTime

data class Appointment(
    val patient: String,
    val professional: String,
    val professionalId: Long,    
    val especiality: String,
    @JsonAdapter(DateTimeHelper::class)
    val date: LocalDateTime
)
