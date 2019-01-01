package com.stylingandroid.muselee.topartists.di

import dagger.Module
import dagger.Provides

@Module
class NetworkModule {

    @Provides
    fun testString() = "Hello World!"

//    private var cacheSize = (5 * 1024 * 1024).toLong()
//
//    @Provides
//    @JvmStatic
//    @Singleton
//    internal fun providesCache(context: Application): Cache =
//        Cache(context.cacheDir, cacheSize)
//
//    @Provides
//    @Named("Cache")
//    @JvmStatic
//    internal fun providesCacheInterceptor(connectivityChecker: ConnectivityChecker): Interceptor =
//        Interceptor { chain ->
//            var newRequest = chain.request()
//            newRequest = if (connectivityChecker.isConnected) {
//                newRequest
//                    .newBuilder()
//                    .header("Cache-Control", "public, max-age=" + TimeUnit.MINUTES.toSeconds(1))
//                    .build()
//            } else {
//                newRequest
//                    .newBuilder()
//                    .header("Cache-Control", "public, only-if-cached, max-stale=" + TimeUnit.DAYS.toSeconds(7))
//                    .build()
//            }
//            chain.proceed(newRequest)
//        }
//
//    @Provides
//    @JvmStatic
//    internal fun providesLoggingInterceptor(): HttpLoggingInterceptor? =
//        if (BuildConfig.DEBUG) {
//            HttpLoggingInterceptor().apply {
//                level = HttpLoggingInterceptor.Level.BODY
//            }
//        } else null
//
//    @Provides
//    @JvmStatic
//    internal fun providesOkHttpClientBuilder(
//        loggingInterceptor: HttpLoggingInterceptor?,
//        @Named("Cache") cacheInterceptor: Interceptor,
//        cache: Cache
//    ): OkHttpClient.Builder =
//        OkHttpClient.Builder()
//            .apply {
//                loggingInterceptor?.also {
//                    addInterceptor(it)
//                }
//            }
//            .addNetworkInterceptor(cacheInterceptor)
//            .cache(cache)

}
