package com.dyah0134.dinosaurus.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header

private const val BASE_URL = "https://kogenkode.my.id/dyah/"

private val  retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface DinoApiService {
    @GET("api.php")
    suspend fun getDino(
        @Header("Authorization") userEmail : String,
    ): String
}

object DinoApi {
    val service: DinoApiService by lazy {
        retrofit.create(DinoApiService::class.java)
    }
}