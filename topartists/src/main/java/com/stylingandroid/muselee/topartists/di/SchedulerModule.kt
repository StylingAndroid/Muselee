package com.stylingandroid.muselee.topartists.di

import com.stylingandroid.muselee.providers.UpdateScheduler
import com.stylingandroid.muselee.topartists.entities.Artist
import com.stylingandroid.muselee.topartists.scheduler.TopArtistsScheduler
import dagger.Module
import dagger.Provides

@Module
object SchedulerModule {

    @Provides
    @JvmStatic
    fun providesScheduler(): UpdateScheduler<Artist> =
        TopArtistsScheduler()

}
