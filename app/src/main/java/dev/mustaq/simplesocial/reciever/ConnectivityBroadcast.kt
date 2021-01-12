package dev.mustaq.simplesocial.reciever

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import dev.mustaq.simplesocial.helper.defaultValue
import dev.mustaq.simplesocial.helper.isInternetAvailable


/**
Created by Mustaq Sameer on 12/1/21
 **/
class ConnectivityBroadcast : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (connectivityReceiverListener != null)
            connectivityReceiverListener!!.onNetworkConnectionChanged(isConnected(context))
    }

    private fun isConnected(context: Context?): Boolean = context?.isInternetAvailable().defaultValue()

    interface ConnectivityReceiverListener {
        fun onNetworkConnectionChanged(isConnected: Boolean)
    }

    companion object {
        var connectivityReceiverListener: ConnectivityReceiverListener? = null
    }
}