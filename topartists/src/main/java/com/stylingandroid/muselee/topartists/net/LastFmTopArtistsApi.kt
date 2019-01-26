package com.stylingandroid.muselee.topartists.net

import retrofit2.Call
import retrofit2.http.GET

interface LastFmTopArtistsApi {

    @GET("?method=chart.gettopartists&format=json")
    fun getTopArtists(): Call<LastFmArtists>
}
