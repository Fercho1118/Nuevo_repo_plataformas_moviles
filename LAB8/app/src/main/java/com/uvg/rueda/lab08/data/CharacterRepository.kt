package com.uvg.rueda.lab08.data

import com.uvg.rueda.lab08.network.CharacterApiService

class CharacterRepository(
    private val characterDao: CharacterDao,
    private val apiService: CharacterApiService
) {

    suspend fun insertInitialCharacters() {
        try {
            val charactersFromApi = apiService.getCharacters()
            characterDao.insertCharacters(charactersFromApi.map { character ->
                CharacterEntity(
                    id = character.id,
                    name = character.name,
                    status = character.status,
                    species = character.species,
                    gender = character.gender,
                    image = character.image
                )
            })
        } catch (e: Exception){
            e.printStackTrace()
        }
    }

    suspend fun getAllCharacters(): List<CharacterEntity> {
        return try {
            val charactersFromApi = apiService.getCharacters()
            characterDao.insertCharacters(charactersFromApi.map { character ->
                CharacterEntity(
                    id = character.id,
                    name = character.name,
                    status = character.status,
                    species = character.species,
                    gender = character.gender,
                    image = character.image
                )
            })
            characterDao.getAllCharacters()
        } catch (e: Exception) {
            characterDao.getAllCharacters()
        }
    }

    suspend fun getCharacterById(id: Int): CharacterEntity {
        return characterDao.getCharacterById(id)
    }
}

