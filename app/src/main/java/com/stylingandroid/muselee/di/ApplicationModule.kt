package com.stylingandroid.muselee.di

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.stylingandroid.muselee.MuseleeApplication
import com.stylingandroid.muselee.connectivity.ConnectivityLiveData
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object ApplicationModule {

    @Provides
    @JvmStatic
    @Singleton
    internal fun provideApplication(app: MuseleeApplication): Application = app

    @Provides
    @JvmStatic
    @Singleton
    fun providesConnectivityManager(app: Application): ConnectivityManager =
        app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    @Provides
    @JvmStatic
    @Singleton
    fun providesConnectivityLiveData(app: Application) =
        ConnectivityLiveData(app)

}
