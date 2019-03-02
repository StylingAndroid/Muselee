package com.stylingandroid.muselee.topartists.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TopArtistsDao {

    @Insert
    fun insertTopArtists(artists: List<DbArtist>)

    @Insert
    fun insertImages(artists: List<DbImage>)

    @Query("SELECT * FROM DbArtist")
    fun getAllArtists(): List<DbArtist>

    @Query("SELECT * FROM DbImage")
    fun getAllImages(): List<DbImage>

    @Query("DELETE FROM DbArtist WHERE expiry < :target")
    fun deleteOutdated(target: Long)

    @Query("DELETE From DbArtist")
    fun deleteAll()
}
