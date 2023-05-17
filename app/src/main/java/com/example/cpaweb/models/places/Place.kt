package com.example.cpaweb.models.places

import com.example.cpaweb.models.Address

data class Place(
    val idLugar: Long = 0L,
    val nomeLugar: String = "",
    val observacoes: String = "",
    val imageUrl: String = "",
    val address: Address? = null
)
