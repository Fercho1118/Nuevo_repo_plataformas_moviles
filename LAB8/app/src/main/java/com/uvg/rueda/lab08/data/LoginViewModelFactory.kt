package com.uvg.rueda.lab08.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.uvg.rueda.lab08.data.CharacterRepository
import com.uvg.rueda.lab08.data.LocationRepository
import com.uvg.rueda.lab08.data.UserRepository

class LoginViewModelFactory(
    private val userRepository: UserRepository,
    private val characterRepository: CharacterRepository,
    private val locationRepository: LocationRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(userRepository, characterRepository, locationRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
