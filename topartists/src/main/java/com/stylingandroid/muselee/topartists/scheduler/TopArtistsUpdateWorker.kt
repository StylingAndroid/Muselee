package com.stylingandroid.muselee.topartists.scheduler

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.stylingandroid.muselee.providers.DataPersister
import com.stylingandroid.muselee.providers.DataProvider
import com.stylingandroid.muselee.providers.UpdateScheduler
import com.stylingandroid.muselee.topartists.entities.Artist
import com.stylingandroid.muselee.topartists.entities.TopArtistsState

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
}
