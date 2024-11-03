package com.uvg.rueda.lab08.network

import com.uvg.rueda.lab08.data.Character
import retrofit2.http.GET

interface CharacterApiService {
    @GET("character")
    suspend fun getCharacters(): List<Character>
}
