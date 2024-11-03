package com.uvg.rueda.lab08.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.uvg.rueda.lab08.data.CharacterRepository

class CharactersViewModelFactory(
    private val characterRepository: CharacterRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharactersViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CharactersViewModel(characterRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
