package com.uvg.rueda.lab08.locations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uvg.rueda.lab08.data.Location
import com.uvg.rueda.lab08.data.LocationRepository
import com.uvg.rueda.lab08.data.UiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LocationsViewModel(
    private val locationRepository: LocationRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState<List<Location>>(isLoading = true))
    val uiState: StateFlow<UiState<List<Location>>> = _uiState

    init {
        loadLocations()
    }

    private fun loadLocations() {
        viewModelScope.launch {
            try {
                delay(4000)

                if (_uiState.value.hasError) return@launch
                
                val locationsFromApi = locationRepository.getAllLocations().map { entity ->
                    Location(
                        id = entity.id,
                        name = entity.name,
                        type = entity.type,
                        dimension = entity.dimension
                    )
                }

                _uiState.value = UiState(isLoading = false, data = locationsFromApi)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(hasError = true)
            }
        }
    }

    fun triggerError() {
        _uiState.value = _uiState.value.copy(hasError = true, isLoading = false)
    }

    fun retry() {
        _uiState.value = UiState(isLoading = true)
        loadLocations()
    }
}
