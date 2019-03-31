package com.stylingandroid.muselee.connectivity

import android.annotation.TargetApi
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.Network
import android.os.Build

internal sealed class ConnectionMonitor(
    protected val connectivityManager: ConnectivityManager
) {

    protected var callbackFunction: ((Boolean) -> Unit) = {}

    abstract fun startListening(callback: (Boolean) -> Unit)
    abstract fun stopListening()

    @TargetApi(Build.VERSION_CODES.N)
    private class NougatConnectionMonitor(connectivityManager: ConnectivityManager) :
        ConnectionMonitor(connectivityManager) {

        private val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                callbackFunction(true)
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                callbackFunction(false)
            }
        }

        override fun startListening(callback: (Boolean) -> Unit) {
            callbackFunction = callback
            callbackFunction(false)
            connectivityManager.registerDefaultNetworkCallback(networkCallback)
        }

        override fun stopListening() {
            connectivityManager.unregisterNetworkCallback(networkCallback)
            callbackFunction = {}
        }
    }

    @Suppress("Deprecation")
    private class LegacyConnectionMonitor(
        private val context: Context,
        connectivityManager: ConnectivityManager
    ) : ConnectionMonitor(connectivityManager) {

        private val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)

        private val isNetworkConnected: Boolean
            get() = connectivityManager.activeNetworkInfo?.isConnected == true

        override fun startListening(callback: (Boolean) -> Unit) {
            callbackFunction = callback
            callbackFunction(isNetworkConnected)
            context.registerReceiver(receiver, filter)
        }

        override fun stopListening() {
            context.unregisterReceiver(receiver)
            callbackFunction = {}
        }

        private val receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                callbackFunction(isNetworkConnected)
            }
        }
    }

    companion object {
        fun getInstance(context: Context): ConnectionMonitor {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                NougatConnectionMonitor(connectivityManager)
            } else {
                LegacyConnectionMonitor(context, connectivityManager)
            }
        }
    }
}
