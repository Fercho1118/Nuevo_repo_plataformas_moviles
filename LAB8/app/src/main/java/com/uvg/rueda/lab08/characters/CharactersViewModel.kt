package com.uvg.rueda.lab08.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uvg.rueda.lab08.data.Character
import com.uvg.rueda.lab08.data.CharacterRepository
import com.uvg.rueda.lab08.data.UiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val characterRepository: CharacterRepository
) : ViewModel() {

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

                val charactersFromApi = characterRepository.getAllCharacters().map { entity ->
                    Character(
                        id = entity.id,
                        name = entity.name,
                        status = entity.status,
                        species = entity.species,
                        gender = entity.gender,
                        image = entity.image
                    )
                }

                _uiState.value = UiState(isLoading = false, data = charactersFromApi)
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
