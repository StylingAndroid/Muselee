package com.stylingandroid.muselee.topartists.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DbArtist::class, DbImage::class], version = 1, exportSchema = false)
abstract class TopArtistsDatabase : RoomDatabase() {

    abstract fun topArtistsDao(): TopArtistsDao
}
