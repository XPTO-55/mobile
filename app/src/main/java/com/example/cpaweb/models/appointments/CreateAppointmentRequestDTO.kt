package com.example.cpaweb.models.appointments

data class CreateAppointmentRequestDTO(
    val patientId: Long? = 0L,
    val profissionalId: Long? = 0L,
)
