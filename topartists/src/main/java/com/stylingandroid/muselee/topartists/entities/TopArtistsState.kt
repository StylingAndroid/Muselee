package com.stylingandroid.muselee.topartists.entities

sealed class TopArtistsState {

    object Loading : TopArtistsState()

    class Success(val artists: List<Artist>) : TopArtistsState()

    class Error(val message: String) : TopArtistsState()
}
