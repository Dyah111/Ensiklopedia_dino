package com.dyah0134.dinosaurus.ui.screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dyah0134.dinosaurus.model.Dino
import com.dyah0134.dinosaurus.network.DinoApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

enum class ApiStatus { LOADING, SUCCESS, ERROR }

class MainViewModel : ViewModel() {

    var data = mutableStateOf(emptyList<Dino>())
        private set

    var status = mutableStateOf(ApiStatus.LOADING)
        private set

    private val userEmail = "test@email.com" // ganti sesuai user email

    init {
        retrieveData()
    }

    private fun retrieveData() {
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
}