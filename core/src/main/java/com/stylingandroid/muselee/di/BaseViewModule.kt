package com.stylingandroid.muselee.di

import androidx.lifecycle.ViewModelProvider
import com.stylingandroid.muselee.view.ViewModelFactory
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class BaseViewModule {

    @Singleton
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory) : ViewModelProvider.Factory
}
