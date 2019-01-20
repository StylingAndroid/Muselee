package com.stylingandroid.muselee.topartists.model

data class Artist(
        val name: String,
        val images: Map<ImageSize, String>
)

enum class ImageSize {
    SMALL,
    MEDIUM,
    LARGE,
    EXTRA_LARGE,
    UNKNOWN
}
