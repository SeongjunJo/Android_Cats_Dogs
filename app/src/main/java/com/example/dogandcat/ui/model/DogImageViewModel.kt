package com.example.dogandcat.ui.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogandcat.network.ApiClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DogImageViewModel : ViewModel() {
    private val _dogUrl = MutableStateFlow<String?>(null)
    val dogUrl: StateFlow<String?> = _dogUrl

    fun fetchDogImage() {
        viewModelScope.launch {
            val response = ApiClient.dogApi.getRandomDogImage()
            _dogUrl.value = response.message
        }
    }
}