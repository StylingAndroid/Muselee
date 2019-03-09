package com.stylingandroid.muselee.topartists.scheduler

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.stylingandroid.muselee.providers.DataPersister
import com.stylingandroid.muselee.providers.DataProvider
import com.stylingandroid.muselee.providers.UpdateScheduler
import com.stylingandroid.muselee.topartists.di.TopArtistsModule
import com.stylingandroid.muselee.topartists.entities.Artist
import com.stylingandroid.muselee.topartists.entities.TopArtistsState
import com.stylingandroid.muselee.work.DaggerWorkerFactory
import javax.inject.Inject
import javax.inject.Named

class TopArtistsUpdateWorker(
    private val provider: DataProvider<TopArtistsState>,
    private val persister: DataPersister<List<Artist>>,
    private val scheduler: UpdateScheduler<Artist>,
    context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {

    override fun doWork(): Result =
        when(val state = provider.requestData()) {
            is TopArtistsState.Success -> {
                persister.persistData(state.artists)
                scheduler.scheduleUpdate(state.artists)
                Result.success()
            }
            is TopArtistsState.Error -> Result.retry()
            is TopArtistsState.Loading -> throw IllegalStateException("Unexpected Loading State")
        }

    class Factory @Inject constructor(
        @Named(TopArtistsModule.NETWORK) private val provider: DataProvider<TopArtistsState>,
        private val persister: DataPersister<List<Artist>>,
        private val scheduler: UpdateScheduler<Artist>
    ) : DaggerWorkerFactory.ChildWorkerFactory {

        override fun create(appContext: Context, params: WorkerParameters): ListenableWorker =
            TopArtistsUpdateWorker(provider, persister, scheduler, appContext, params)
    }
}
