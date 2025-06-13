package com.dyah0134.dinosaurus.model

data class Dino(
    val id: Int,
    val imagepath: String,
    val nama: String,
    val jenis: String,
    val imageUrl: String? = null
)

data class DinoResponse(
    val data: List<Dino>
)