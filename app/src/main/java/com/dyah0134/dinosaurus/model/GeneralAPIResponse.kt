package com.dyah0134.dinosaurus.model

data class GeneralAPIResponse(
    var status: String,
    var message: String? = "",
    val id: Int? = null,
    val imagepath : String? = null,
    val nama : String? = null,
    val jenis : String? = null,
    val upload_date : String? = null,
    val imageUrl : String?= null
)