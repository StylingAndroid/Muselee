package com.stylingandroid.muselee.providers

interface UpdateScheduler<T> {
    fun scheduleUpdate(items: List<T>)
}
