package com.uvg.rueda.lab08.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uvg.rueda.lab08.data.Character
import com.uvg.rueda.lab08.data.CharacterDb
import com.uvg.rueda.lab08.data.UiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CharactersViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(UiState<List<Character>>(isLoading = true))
    val uiState: StateFlow<UiState<List<Character>>> = _uiState

    init {
        loadCharacters()
    }

    private fun loadCharacters() {
        viewModelScope.launch {
            try {
                delay(4000)
                if (_uiState.value.hasError) return@launch
                _uiState.value = UiState(isLoading = false, data = CharacterDb().getAllCharacters())
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
        loadCharacters()
    }
}