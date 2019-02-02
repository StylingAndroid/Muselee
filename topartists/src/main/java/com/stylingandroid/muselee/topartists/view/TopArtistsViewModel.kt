package com.stylingandroid.muselee.topartists.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stylingandroid.muselee.providers.DataProvider
import com.stylingandroid.muselee.topartists.entities.TopArtistsState
import javax.inject.Inject

class TopArtistsViewModel @Inject constructor(
    private val topArtistsProvider: DataProvider<TopArtistsState>
) : ViewModel() {

    private val mutableLiveData: MutableLiveData<TopArtistsViewState> = MutableLiveData()
    val topArtistsViewState: LiveData<TopArtistsViewState>
        get() = mutableLiveData

    init {
        load()
    }

    fun load() {
        topArtistsProvider.requestData { artistsState ->
            mutableLiveData.value = when (artistsState) {
                TopArtistsState.Loading -> TopArtistsViewState.InProgress
                is TopArtistsState.Error -> TopArtistsViewState.ShowError(artistsState.message)
                is TopArtistsState.Success -> TopArtistsViewState.ShowTopArtists(artistsState.artists)
            }
        }
    }
}
