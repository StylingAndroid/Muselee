package com.stylingandroid.muselee.topartists.view

import com.stylingandroid.muselee.topartists.entities.Artist

sealed class TopArtistsViewState {

    object InProgress : TopArtistsViewState()

    class ShowTopArtists(val topArtists: List<Artist>) : TopArtistsViewState()

    class ShowError(val message: String) : TopArtistsViewState()
}
