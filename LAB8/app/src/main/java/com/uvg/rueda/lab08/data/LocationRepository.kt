package com.uvg.rueda.lab08.data

class LocationRepository(private val locationDao: LocationDao) {

    suspend fun insertInitialLocations() {
        val initialLocations = LocationDb().getAllLocations()
        locationDao.insertLocations(initialLocations.map { location ->
            LocationEntity(
                id = location.id,
                name = location.name,
                type = location.type,
                dimension = location.dimension
            )
        })
    }

    suspend fun getAllLocations(): List<LocationEntity> {
        return locationDao.getAllLocations()
    }

    suspend fun getLocationById(id: Int): LocationEntity {
        return locationDao.getLocationById(id)
    }
}

