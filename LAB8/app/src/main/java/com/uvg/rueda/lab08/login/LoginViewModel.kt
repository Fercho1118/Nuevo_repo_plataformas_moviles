package com.uvg.rueda.lab08.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uvg.rueda.lab08.data.CharacterRepository
import com.uvg.rueda.lab08.data.LocationRepository
import com.uvg.rueda.lab08.data.UserRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val userRepository: UserRepository,
    private val characterRepository: CharacterRepository,
    private val locationRepository: LocationRepository
) : ViewModel() {

    private val _nameState = MutableStateFlow<String?>(null)
    val nameState: StateFlow<String?> get() = _nameState

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    init {
        checkIfLoggedIn()
    }

    fun checkIfLoggedIn() {
        viewModelScope.launch {
            userRepository.userNameFlow.collect { name ->
                _nameState.value = name
            }
        }
    }

    fun saveUserName(name: String, onNavigateToCharacters: () -> Unit) {
        viewModelScope.launch {
            userRepository.saveUserName(name)
            syncInitialData(onNavigateToCharacters)
        }
    }

    private fun syncInitialData(onNavigateToCharacters: () -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            delay(2000)

            characterRepository.insertInitialCharacters()
            locationRepository.insertInitialLocations()

            _isLoading.value = false
            onNavigateToCharacters()
        }
    }

    fun logout() {
        viewModelScope.launch {
            userRepository.clearUserName()
        }
    }
}
