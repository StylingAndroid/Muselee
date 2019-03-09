package com.stylingandroid.muselee.topartists.di

import com.stylingandroid.muselee.providers.DataPersister
import com.stylingandroid.muselee.providers.DataProvider
import com.stylingandroid.muselee.providers.UpdateScheduler
import com.stylingandroid.muselee.topartists.entities.Artist
import com.stylingandroid.muselee.topartists.entities.TopArtistsRepository
import com.stylingandroid.muselee.topartists.entities.TopArtistsState
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
object EntitiesModule {

    @Provides
    @Named(TopArtistsModule.ENTITIES)
    @JvmStatic
    internal fun providesTopArtistsRepository(
        persistence: DataPersister<List<Artist>>,
        @Named(TopArtistsModule.NETWORK) provider: DataProvider<TopArtistsState>,
        scheduler: UpdateScheduler<Artist>
    ): DataProvider<TopArtistsState> = TopArtistsRepository(persistence, provider, scheduler)
}
