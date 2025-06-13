package com.dyah0134.dinosaurus.model

data class Dino(
    val id: Int,
    val imagpath: String,
    val nama: String,
    val jenis: String,
    val imageUrl: String?= null,
)
