package com.stylingandroid.muselee.lastfm.net

import com.squareup.moshi.Json

data class LastFmArtists(val artists: ArtistsList)

data class ArtistsList(val artist: List<LastFmArtist>)

data class LastFmArtist(
        val name: String,
        @field:Json(name = "playcount") val playCount: Long,
        val listeners: Long,
        val mbid: String,
        val url: String,
        val streamable: Int,
        @field:Json(name = "image") val images: List<LastFmImage>
)

data class LastFmImage(
        @field:Json(name = "#text") val url: String,
        val size: String
)
