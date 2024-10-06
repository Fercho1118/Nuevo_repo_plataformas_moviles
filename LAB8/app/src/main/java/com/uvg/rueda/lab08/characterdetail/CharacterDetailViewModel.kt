package com.uvg.rueda.lab08.characterdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uvg.rueda.lab08.data.Character
import com.uvg.rueda.lab08.data.CharacterDb
import com.uvg.rueda.lab08.data.UiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CharacterDetailViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState<Character>(isLoading = true))
    val uiState: StateFlow<UiState<Character>> = _uiState

    private val characterId: Int = savedStateHandle.get<String>("characterId")?.toIntOrNull() ?: 0

    init {
        loadCharacterDetail()
    }

    private fun loadCharacterDetail() {
        viewModelScope.launch {
            try {
                delay(2000)
                if (_uiState.value.hasError) return@launch
                val character = CharacterDb().getCharacterById(characterId)
                _uiState.value = UiState(isLoading = false, data = character)
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
        loadCharacterDetail()
    }
}