package com.example.cpaweb.models

import java.io.Serializable

data class Address(
    val id: Long = 0L,
    val street: String = "",
    val district: String = "",
    val number: String = "",
    val complement: String = "",
    val zipcode: String = "",
    val city: String = "",
    val uf: String = "",
    val url: String? = ""
): Serializable
