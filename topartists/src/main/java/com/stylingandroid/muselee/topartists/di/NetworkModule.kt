package com.stylingandroid.muselee.topartists.di

import dagger.Module
import dagger.Provides

@Module
class NetworkModule {

    @Provides
    fun testString() = "Hello World!"
    
}
