package com.stylingandroid.muselee.di

import com.stylingandroid.muselee.MuseleeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
@Suppress("unused")
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun bindMuseleeActivity(): MuseleeActivity

}
