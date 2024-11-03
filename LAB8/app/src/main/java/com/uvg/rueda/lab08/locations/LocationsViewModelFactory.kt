package com.uvg.rueda.lab08.locations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.uvg.rueda.lab08.data.LocationRepository

class LocationsViewModelFactory(
    private val locationRepository: LocationRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(LocationsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            LocationsViewModel(locationRepository) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
