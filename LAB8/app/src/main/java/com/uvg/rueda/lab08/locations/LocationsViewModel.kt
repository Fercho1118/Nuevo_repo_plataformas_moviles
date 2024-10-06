package com.uvg.rueda.lab08.locations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uvg.rueda.lab08.data.Location
import com.uvg.rueda.lab08.data.LocationDb
import com.uvg.rueda.lab08.data.UiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LocationsViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(UiState<List<Location>>(isLoading = true))
    val uiState: StateFlow<UiState<List<Location>>> = _uiState

    init {
        loadLocations()
    }

    private fun loadLocations() {
        viewModelScope.launch {
            delay(4000)
            val locations = LocationDb().getAllLocations()
            _uiState.value = UiState(
                isLoading = false,
                data = locations
            )
        }
    }

    fun triggerError() {
        _uiState.value = _uiState.value.copy(hasError = true)
    }

    fun retry() {
        _uiState.value = _uiState.value.copy(isLoading = true, hasError = false)
        loadLocations()
    }
}