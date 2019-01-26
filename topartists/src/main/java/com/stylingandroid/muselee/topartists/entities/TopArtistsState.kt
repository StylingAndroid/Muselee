package com.stylingandroid.muselee.topartists.entities

sealed class TopArtistsState {

    object Loading : TopArtistsState()

    class Success(val artists: List<Artist>) : TopArtistsState()

    class Error(val message: String) : TopArtistsState()

    data class Artist(val name: String, val images: Map<ImageSize, String>)

    enum class ImageSize {
        SMALL,
        MEDIUM,
        LARGE,
        EXTRA_LARGE,
        UNKNOWN
    }

}
