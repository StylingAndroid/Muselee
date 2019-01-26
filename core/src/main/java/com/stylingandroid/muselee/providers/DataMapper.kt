package com.stylingandroid.muselee.providers

interface DataMapper<S, R> {
    fun map(source: S): R
}
