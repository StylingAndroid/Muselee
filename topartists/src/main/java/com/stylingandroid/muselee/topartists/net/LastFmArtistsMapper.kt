package com.stylingandroid.muselee.topartists.net

import com.stylingandroid.muselee.lastfm.net.LastFmArtist
import com.stylingandroid.muselee.lastfm.net.LastFmArtists
import com.stylingandroid.muselee.providers.DataMapper
import com.stylingandroid.muselee.topartists.entities.Artist
import com.stylingandroid.muselee.topartists.entities.ImageSize

class LastFmArtistsMapper : DataMapper<LastFmArtists, List<Artist>> {

    override fun map(source: LastFmArtists): List<Artist> =
        source.artists.artist.map { artist ->
            Artist(artist.name, artist.normalisedImages())
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
