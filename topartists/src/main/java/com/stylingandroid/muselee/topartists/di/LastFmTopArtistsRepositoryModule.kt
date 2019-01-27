package com.stylingandroid.muselee.topartists.di

import com.stylingandroid.muselee.app.ConnectivityChecker
import com.stylingandroid.muselee.providers.DataMapper
import com.stylingandroid.muselee.providers.DataProvider
import com.stylingandroid.muselee.topartists.entities.Artist
import com.stylingandroid.muselee.topartists.entities.TopArtistsState
import com.stylingandroid.muselee.topartists.net.LastFmArtists
import com.stylingandroid.muselee.topartists.net.LastFmArtistsMapper
import com.stylingandroid.muselee.topartists.net.LastFmTopArtistsApi
import com.stylingandroid.muselee.topartists.net.LastFmTopArtistsProvider
import dagger.Module
import dagger.Provides

@Module
object LastFmTopArtistsRepositoryModule {

    @Provides
    @JvmStatic
    fun providesTopArtistsProvider(
        lastFmTopArtistsApi: LastFmTopArtistsApi,
        connectivityChecker: ConnectivityChecker,
        mapper: DataMapper<LastFmArtists, List<Artist>>
    ): DataProvider<TopArtistsState> =
        LastFmTopArtistsProvider(
            lastFmTopArtistsApi,
            connectivityChecker,
            mapper
        )

    @Provides
    @JvmStatic
    fun providesLastFmMapper(): DataMapper<LastFmArtists, List<Artist>> =
        LastFmArtistsMapper()
}
