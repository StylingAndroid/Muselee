package com.stylingandroid.muselee.di

import com.stylingandroid.muselee.MuseleeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ApplicationModule {

    @ContributesAndroidInjector
    abstract fun bindMuseleeActivity(): MuseleeActivity
}
