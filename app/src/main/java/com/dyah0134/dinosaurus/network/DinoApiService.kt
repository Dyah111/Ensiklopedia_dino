package com.dyah0134.dinosaurus.network

import com.dyah0134.dinosaurus.model.DinoResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header

private const val BASE_URL = "https://kogenkode.my.id/dyah/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface DinoApiService {
    @GET("api.php")
    suspend fun getDino(
        @Header("Authorization") userEmail: String
    ): DinoResponse
}

object DinoApi {
    val service: DinoApiService by lazy {
        retrofit.create(DinoApiService::class.java)
    }

    fun getDinoUrl(imagepath: String): String {
        return BASE_URL + imagepath
    }
}

enum class ApiStatus { LOADING, SUCCESS, ERROR }