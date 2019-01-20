package com.stylingandroid.muselee.di

import com.stylingandroid.muselee.core.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

@Module
object CoreNetworkModule {

    @Provides
    @JvmStatic
    internal fun providesLoggingInterceptor(): HttpLoggingInterceptor? =
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        } else null

    @Provides
    @JvmStatic
    internal fun providesOkHttpClientBuilder(
        loggingInterceptor: HttpLoggingInterceptor?,
        cache: Cache
    ): OkHttpClient.Builder =
        OkHttpClient.Builder()
            .apply {
                loggingInterceptor?.also {
                    addInterceptor(it)
                }
            }
            .cache(cache)
}
