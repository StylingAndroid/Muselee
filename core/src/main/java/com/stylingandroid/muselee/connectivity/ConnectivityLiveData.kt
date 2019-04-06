package com.stylingandroid.muselee.connectivity

import android.content.Context
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class ConnectivityLiveData @Inject constructor(context: Context) :
    MutableLiveData<ConnectivityState>() {

    private val connectionMonitor = ConnectivityMonitor.getInstance(context.applicationContext)

    override fun onActive() {
        super.onActive()
        connectionMonitor.startListening(::setConnected)
    }

    override fun onInactive() {
        connectionMonitor.stopListening()
        super.onInactive()
    }

    private fun setConnected(isConnected: Boolean) =
        postValue(if (isConnected) ConnectivityState.Connected else ConnectivityState.Disconnected)
}
