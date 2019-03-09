package com.stylingandroid.muselee.topartists.di

import androidx.lifecycle.ViewModel
import com.stylingandroid.muselee.di.BaseViewModule
import com.stylingandroid.muselee.di.ViewModelKey
import com.stylingandroid.muselee.di.WorkerKey
import com.stylingandroid.muselee.topartists.scheduler.TopArtistsUpdateWorker
import com.stylingandroid.muselee.topartists.view.TopArtistsFragment
import com.stylingandroid.muselee.topartists.view.TopArtistsViewModel
import com.stylingandroid.muselee.work.DaggerWorkerFactory
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(
    includes = [
        EntitiesModule::class,
        DatabaseModule::class,
        NetworkModule::class,
        BaseViewModule::class,
        SchedulerModule::class,
        LastFmTopArtistsModule::class
    ]
)
@Suppress("unused")
abstract class TopArtistsModule {

    companion object {
        const val ENTITIES = "ENTITIES"
        const val NETWORK = "NETWORK"
    }

    @ContributesAndroidInjector
    abstract fun bindTopArtistsFragment(): TopArtistsFragment

    @Binds
    @IntoMap
    @ViewModelKey(TopArtistsViewModel::class)
    abstract fun bindChartsViewModel(viewModel: TopArtistsViewModel): ViewModel

    @Binds
    @IntoMap
    @WorkerKey(TopArtistsUpdateWorker::class)
    abstract fun bindTopArtistsUpdateWorker(factory: TopArtistsUpdateWorker.Factory):
            DaggerWorkerFactory.ChildWorkerFactory
}
