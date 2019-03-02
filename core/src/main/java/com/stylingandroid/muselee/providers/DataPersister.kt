package com.stylingandroid.muselee.providers

interface DataPersister<T> : DataProvider<T> {
    fun persistData(data: T)
}
