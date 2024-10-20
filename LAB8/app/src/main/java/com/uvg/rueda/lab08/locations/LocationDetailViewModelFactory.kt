package com.uvg.rueda.lab08.locations

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class LocationDetailViewModelFactory(
    private val locationId: Int
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LocationDetailViewModel::class.java)) {
            return LocationDetailViewModel(SavedStateHandle(mapOf("locationId" to locationId))) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}