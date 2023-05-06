package com.example.cpaweb.models

data class AvaliacaoFeedback(
    val idProfissional: Long,
    val idUsuario: Long,
    val texto: String,
    val nota: Int,
)
