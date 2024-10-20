package com.uvg.rueda.lab08.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CharacterEntity::class, LocationEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun locationDao(): LocationDao
}
