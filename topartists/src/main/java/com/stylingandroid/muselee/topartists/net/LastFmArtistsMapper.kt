package com.stylingandroid.muselee.topartists.net

import com.stylingandroid.muselee.providers.DataMapper
import com.stylingandroid.muselee.topartists.entities.Artist
import com.stylingandroid.muselee.topartists.entities.Artist.ImageSize

class LastFmArtistsMapper : DataMapper<Pair<LastFmArtists, Long>, List<Artist>> {

    override fun encode(source: Pair<LastFmArtists, Long>): List<Artist> {
        val (lastFmArtists, expiry) = source
        return lastFmArtists.artists.artist.map { artist ->
            Artist(artist.name, artist.normalisedImages(), expiry)
        }
    }

    private fun LastFmArtist.normalisedImages() =
        images.map { it.size.toImageSize() to it.url }.toMap()

    private fun String.toImageSize(): ImageSize =
        when (this) {
            "small" -> ImageSize.SMALL
            "medium" -> ImageSize.MEDIUM
            "large" -> ImageSize.LARGE
            "extralarge" -> ImageSize.EXTRA_LARGE
            else -> ImageSize.UNKNOWN
        }
}
