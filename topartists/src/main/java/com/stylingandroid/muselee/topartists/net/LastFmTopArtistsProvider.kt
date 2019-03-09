package com.stylingandroid.muselee.topartists.net

import com.stylingandroid.muselee.app.ConnectivityChecker
import com.stylingandroid.muselee.providers.DataMapper
import com.stylingandroid.muselee.providers.DataProvider
import com.stylingandroid.muselee.topartists.entities.Artist
import com.stylingandroid.muselee.topartists.entities.TopArtistsState
import okhttp3.internal.http.HttpDate
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.TimeUnit

class LastFmTopArtistsProvider(
    private val topArtistsApi: LastFmTopArtistsApi,
    private val connectivityChecker: ConnectivityChecker,
    private val mapper: DataMapper<Pair<LastFmArtists, Long>, List<Artist>>
) : DataProvider<TopArtistsState> {

    override fun requestData(): TopArtistsState {
        return if (!connectivityChecker.isConnected) {
            TopArtistsState.Error("No network connectivity")
        } else {
            val response = topArtistsApi.getTopArtists().execute()
            response.takeIf { it.isSuccessful }?.body()?.let { artists ->
                TopArtistsState.Success(mapper.encode(artists to response.expiry))
            } ?: TopArtistsState.Error(response.errorBody()?.string() ?: "Network Error")
        }
    }

    override fun requestData(callback: (topArtists: TopArtistsState) -> Unit) {
        if (!connectivityChecker.isConnected) {
            callback(TopArtistsState.Error("No network connectivity"))
            return
        }
        callback(TopArtistsState.Loading)
        topArtistsApi.getTopArtists().enqueue(object : Callback<LastFmArtists> {
            override fun onFailure(call: Call<LastFmArtists>, t: Throwable) {
                callback(TopArtistsState.Error(t.localizedMessage))
            }

            override fun onResponse(call: Call<LastFmArtists>, response: Response<LastFmArtists>) {
                response.body()?.also { topArtists ->
                    callback(TopArtistsState.Success(mapper.encode(topArtists to response.expiry)))
                }
            }
        })
    }

    private val Response<LastFmArtists>.expiry: Long
        get() {
            val expires: Long? = if (headers().names().contains(HEADER_EXPIRES)) {
                HttpDate.parse(headers().get(HEADER_EXPIRES)).time
            } else null
            val cacheControlMaxAge = raw().cacheControl().maxAgeSeconds().toLong()
            val maxAge: Long? =
                cacheControlMaxAge.takeIf { it >= 0 } ?: headers().get(HEADER_AC_MAX_AGE)?.toLong()
            val date = if (headers().names().contains(HEADER_DATE)) {
                HttpDate.parse(headers().get(HEADER_DATE)).time
            } else {
                System.currentTimeMillis()
            }
            return expires
                ?: maxAge?.let { date + TimeUnit.SECONDS.toMillis(it) }
                ?: date + TimeUnit.DAYS.toMillis(1)
        }

    companion object {
        private const val HEADER_DATE = "Date"
        private const val HEADER_EXPIRES = "Expires"
        private const val HEADER_AC_MAX_AGE = "Access-Control-Max-Age"
    }
}
