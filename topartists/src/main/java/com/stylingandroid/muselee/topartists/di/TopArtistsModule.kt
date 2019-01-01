package com.stylingandroid.muselee.topartists.di

import com.stylingandroid.muselee.topartists.view.TopArtistsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class TopArtistsModule {

    @ContributesAndroidInjector(
        modules = [NetworkModule::class]
    )
    abstract fun bindTopArtistsFragment(): TopArtistsFragment

}
