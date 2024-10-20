package com.uvg.rueda.lab08.data

class CharacterRepository(private val characterDao: CharacterDao) {

    suspend fun insertInitialCharacters() {
        val initialCharacters = CharacterDb().getAllCharacters()
        characterDao.insertCharacters(initialCharacters.map { character ->
            CharacterEntity(
                id = character.id,
                name = character.name,
                status = character.status,
                species = character.species,
                gender = character.gender,
                image = character.image
            )
        })
    }

    suspend fun getAllCharacters(): List<CharacterEntity> {
        return characterDao.getAllCharacters()
    }

    suspend fun getCharacterById(id: Int): CharacterEntity {
        return characterDao.getCharacterById(id)
    }
}

