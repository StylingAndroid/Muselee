package com.stylingandroid.muselee.topartists.entities

data class Artist(val name: String, val images: Map<Artist.ImageSize, String>, val expiry: Long) {

    enum class ImageSize {
        SMALL,
        MEDIUM,
        LARGE,
        EXTRA_LARGE,
        UNKNOWN
    }
}
