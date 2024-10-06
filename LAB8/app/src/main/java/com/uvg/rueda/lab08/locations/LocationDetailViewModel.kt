package com.uvg.rueda.lab08.locations

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uvg.rueda.lab08.data.Location
import com.uvg.rueda.lab08.data.LocationDb
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class UiState<T>(
    val isLoading: Boolean = false,
    val data: T? = null,
    val hasError: Boolean = false
)

class LocationDetailViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState<Location>(isLoading = true))
    val uiState: StateFlow<UiState<Location>> = _uiState

    private val locationId: Int = savedStateHandle.get<String>("locationId")?.toIntOrNull() ?: 0

    init {
        loadLocationDetail()
    }

    private fun loadLocationDetail() {
        viewModelScope.launch {
            try {
                delay(2000)
                if (_uiState.value.hasError) return@launch
                val location = LocationDb().getLocationById(locationId)
                _uiState.value = UiState(isLoading = false, data = location)
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
        loadLocationDetail()
    }
}