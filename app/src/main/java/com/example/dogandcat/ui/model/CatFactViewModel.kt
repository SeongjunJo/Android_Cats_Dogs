package com.example.dogandcat.ui.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogandcat.network.ApiClient
import com.example.dogandcat.network.CatFact
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CatFactViewModel : ViewModel() {
    private val _facts = MutableStateFlow<List<CatFact>>(emptyList())
    val facts: StateFlow<List<CatFact>> = _facts

    fun fetchFacts() {
        viewModelScope.launch {
            val facts = mutableListOf<CatFact>()
            repeat(5) {
                val fact = ApiClient.catFactService.getCatFact()
                facts.add(fact)
            }
            _facts.value = facts
        }
    }
}