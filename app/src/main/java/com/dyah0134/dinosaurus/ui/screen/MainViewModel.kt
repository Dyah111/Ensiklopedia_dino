package com.dyah0134.dinosaurus.ui.screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dyah0134.dinosaurus.model.Dino
import com.dyah0134.dinosaurus.network.ApiStatus
import com.dyah0134.dinosaurus.network.DinoApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainViewModel : ViewModel() {

    var data = mutableStateOf(emptyList<Dino>())
        private set

    var status = mutableStateOf(ApiStatus.LOADING)
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
}