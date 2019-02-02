package com.stylingandroid.muselee.topartists.di

import androidx.lifecycle.ViewModel
import com.stylingandroid.muselee.di.BaseViewModule
import com.stylingandroid.muselee.di.ViewModelKey
import com.stylingandroid.muselee.topartists.view.TopArtistsFragment
import com.stylingandroid.muselee.topartists.view.TopArtistsViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(
    includes = [
        NetworkModule::class,
        BaseViewModule::class,
        LastFmTopArtistsModule::class
    ]
)
@Suppress("unused")
abstract class TopArtistsModule {

    @ContributesAndroidInjector
    abstract fun bindTopArtistsFragment(): TopArtistsFragment

    @Binds
    @IntoMap
    @ViewModelKey(TopArtistsViewModel::class)
    abstract fun bindChartsViewModel(viewModel: TopArtistsViewModel): ViewModel
}
