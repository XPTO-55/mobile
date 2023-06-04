package com.example.cpaweb.models.appointments

import com.example.cpaweb.models.users.UserBase
import com.example.cpaweb.models.users.professionals.Professional

data class AppointmentData(
    val patient: UserBase? = null,
    val professional: Professional? = null,
)
