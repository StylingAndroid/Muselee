package com.stylingandroid.muselee.lastfm.net

import com.stylingandroid.muselee.providers.DataMapper
import com.stylingandroid.muselee.providers.DataProvider
import com.stylingandroid.muselee.topartists.entities.Artist
import com.stylingandroid.muselee.topartists.entities.TopArtistsState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LastFmTopArtistsProvider(
    private val topArtistsApi: LastFmTopArtistsApi,
    private val mapper: DataMapper<LastFmArtists, List<Artist>>
) : DataProvider<TopArtistsState> {

    override fun requestData(callback: (topArtists: TopArtistsState) -> Unit) {
        callback(TopArtistsState.Loading)
        topArtistsApi.getTopArtists().enqueue(object : Callback<LastFmArtists> {
            override fun onFailure(call: Call<LastFmArtists>, t: Throwable) {
                callback(TopArtistsState.Error(t.localizedMessage))
            }

            override fun onResponse(call: Call<LastFmArtists>, response: Response<LastFmArtists>) {
                response.body()?.also { topArtists ->
                    callback(TopArtistsState.Success(mapper.map(topArtists)))
                }
            }
        })
    }
}
