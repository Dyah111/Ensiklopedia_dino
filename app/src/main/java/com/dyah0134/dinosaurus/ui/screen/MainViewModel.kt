package com.dyah0134.dinosaurus.ui.screen

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dyah0134.dinosaurus.model.Dino
import com.dyah0134.dinosaurus.network.ApiStatus
import com.dyah0134.dinosaurus.network.DinoApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.ByteArrayOutputStream


class MainViewModel : ViewModel() {

    var data = mutableStateOf(emptyList<Dino>())
        private set

    var status = mutableStateOf(ApiStatus.LOADING)
        private set

    var errorMessage = mutableStateOf<String?>(null)
        private set

    fun retrieveData(userEmail : String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                status.value = ApiStatus.LOADING
                val response = DinoApi.service.getDino(userEmail)
                data.value = response.data
                status.value = ApiStatus.SUCCESS
            } catch (e: Exception) {
                Log.e("MainViewModel", "Failure: ${e.message}")
                status.value = ApiStatus.ERROR
            }
        }
    }

    fun saveData(userEmail: String, nama: String, jenis: String, bitmap: Bitmap){
        viewModelScope.launch ( Dispatchers.IO ) {
            try {
                val result = DinoApi.service.postDino(
                    userEmail,
                    nama.toRequestBody("text/plain".toMediaTypeOrNull()),
                    jenis.toRequestBody("text/plain".toMediaTypeOrNull()),
                    bitmap.toMultipartBody()
                )

                if (result.status == "success")
                    retrieveData(userEmail)
                else
                    throw Exception(result.message)
            } catch (e: Exception){
                Log.d("MainViewModel", "Failure: ${e.message}")
                errorMessage.value = "Error: ${e.message}"
            }
        }
    }

    private fun Bitmap.toMultipartBody(): MultipartBody.Part {
        val stream = ByteArrayOutputStream()
        compress(Bitmap.CompressFormat.JPEG, 80, stream)
        val byteArray = stream.toByteArray()
        val requestBody = byteArray.toRequestBody(
            "image/jpg".toMediaTypeOrNull(), 0, byteArray.size)
        return MultipartBody.Part.createFormData(
            "gambar", "image.jpg", requestBody)
    }
    fun clearMessage() { errorMessage.value = null }
}