package com.stylingandroid.muselee.topartists.scheduler

import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.stylingandroid.muselee.providers.UpdateScheduler
import com.stylingandroid.muselee.topartists.entities.Artist
import java.util.concurrent.TimeUnit

class TopArtistsScheduler : UpdateScheduler<Artist> {

    override fun scheduleUpdate(items: List<Artist>) {
        WorkManager.getInstance()
            .enqueueUniqueWork(
                UNIQUE_WORK_ID,
                ExistingWorkPolicy.REPLACE,
                OneTimeWorkRequestBuilder<TopArtistsUpdateWorker>()
                    .setInitialDelay(items.earliestUpdate(), TimeUnit.MILLISECONDS)
                    .setConstraints(
                        Constraints.Builder()
                            .setRequiredNetworkType(NetworkType.UNMETERED)
                            .setRequiresBatteryNotLow(true)
                            .build()
                    )
                    .build()
            )
    }

    private fun List<Artist>.earliestUpdate() =
        (minBy { it.expiry }?.expiry?.let { it - System.currentTimeMillis() }
            ?: TimeUnit.DAYS.toMillis(1)) / 2

    companion object {
        private const val UNIQUE_WORK_ID: String = "TopArtistsScheduler"
    }
}
