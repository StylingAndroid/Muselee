package com.stylingandroid.muselee.topartists.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["name"])])
data class DbArtist(
    @PrimaryKey val rank: Int,
    val name: String,
    val created: Long,
    val expiry: Long
)

@Entity(
    primaryKeys = ["rank", "typeIndex"],
    foreignKeys = [
        ForeignKey(
            entity = DbArtist::class,
            parentColumns = ["rank"],
            childColumns = ["rank"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class DbImage(
    val rank: Int,
    val typeIndex: Int,
    val url: String
)
