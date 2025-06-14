package com.dyah0134.dinosaurus.network

import com.dyah0134.dinosaurus.model.DinoResponse
import com.dyah0134.dinosaurus.model.GeneralAPIResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

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

    @Multipart
    @POST("api.php")
    suspend fun postDino(
        @Header("Authorization") userId: String,
        @Part("nama") nama: RequestBody,
        @Part("jenis") jenis: RequestBody,
        @Part gambar: MultipartBody.Part?
    ) : GeneralAPIResponse
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