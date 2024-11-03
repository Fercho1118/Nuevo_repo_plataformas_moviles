package com.uvg.rueda.lab08.data

import com.uvg.rueda.lab08.network.LocationApiService

class LocationRepository(
    private val locationDao: LocationDao,
    private val apiService: LocationApiService
) {
    suspend fun insertInitialLocations() {
        try {
            val locationsFromApi = apiService.getLocations()
            locationDao.insertLocations(locationsFromApi.map { location ->
                LocationEntity(
                    id = location.id,
                    name = location.name,
                    type = location.type,
                    dimension = location.dimension
                )
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun getAllLocations(): List<LocationEntity> {
        return try {
            val locationsFromApi = apiService.getLocations()
            locationDao.insertLocations(locationsFromApi.map { location ->
                LocationEntity(
                    id = location.id,
                    name = location.name,
                    type = location.type,
                    dimension = location.dimension
                )
            })
            locationDao.getAllLocations()
        } catch (e: Exception) {
            locationDao.getAllLocations()
        }
    }

    suspend fun getLocationById(id: Int): LocationEntity {
        return locationDao.getLocationById(id)
    }
}
