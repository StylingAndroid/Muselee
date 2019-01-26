package com.stylingandroid.muselee.topartists.di

import com.stylingandroid.muselee.topartists.view.TopArtistsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(
    includes = [NetworkModule::class]
)
abstract class TopArtistsModule {

    @ContributesAndroidInjector
    abstract fun bindTopArtistsFragment(): TopArtistsFragment

}
