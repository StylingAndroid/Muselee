package com.stylingandroid.muselee

import androidx.work.Configuration
import androidx.work.WorkManager
import com.stylingandroid.muselee.di.DaggerApplicationComponent
import com.stylingandroid.muselee.work.DaggerWorkerFactory
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import javax.inject.Inject

class MuseleeApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerApplicationComponent.builder().create(this)

    @Inject
    lateinit var workerFactory: DaggerWorkerFactory

    override fun onCreate() {
        super.onCreate()

        WorkManager.initialize(
            this,
            Configuration.Builder()
                .setWorkerFactory(workerFactory)
                .build()
        )

    }
}
