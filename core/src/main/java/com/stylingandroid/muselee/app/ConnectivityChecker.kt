package com.stylingandroid.muselee.app

import android.net.ConnectivityManager
import javax.inject.Inject

class ConnectivityChecker @Inject constructor(private val connectivityManager: ConnectivityManager) {

    val isConnected: Boolean
        get() = connectivityManager.activeNetworkInfo?.isConnected ?: false

}
