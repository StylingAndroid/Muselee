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
import javax.inject.Named

@Module
object LastFmTopArtistsModule {

    @Provides
    @Named(TopArtistsModule.NETWORK)
    @JvmStatic
    fun providesTopArtistsDataProvider(
        lastFmTopArtistsApi: LastFmTopArtistsApi,
        connectivityChecker: ConnectivityChecker,
        mapper: DataMapper<Pair<LastFmArtists, Long>, List<Artist>>
    ): DataProvider<TopArtistsState> =
        LastFmTopArtistsProvider(
            lastFmTopArtistsApi,
            connectivityChecker,
            mapper
        )

    @Provides
    @JvmStatic
    fun providesLastFmMapper(): DataMapper<Pair<LastFmArtists, Long>, List<Artist>> =
        LastFmArtistsMapper()
}
