package com.dyah0134.dinosaurus.model

data class DinoResponse(
    val status: String,
    val data: List<Dino>,
    val message: String? = ""
)
