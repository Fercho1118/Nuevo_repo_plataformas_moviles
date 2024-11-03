package com.uvg.rueda.lab08.network

import com.uvg.rueda.lab08.data.Location
import retrofit2.http.GET

interface LocationApiService {
    @GET("location")
    suspend fun getLocations(): List<Location>
}
