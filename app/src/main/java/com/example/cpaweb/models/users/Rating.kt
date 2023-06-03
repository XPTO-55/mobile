package com.example.cpaweb.models.users

import java.io.Serializable

data class Rating(
    val id: Long,
    val rating: Double,
    val comment: String,
    val profissional: String
): Serializable
