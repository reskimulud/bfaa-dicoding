package com.mankart.myservice

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.*

class MyService : Service() {

    private val serviceJob = Job()
    private val serviceScope = CoroutineScope(Dispatchers.Main + serviceJob)

    companion object {
        internal val TAG = MyService::class.java.simpleName
    }

    override fun onBind(intent: Intent): IBinder {
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "Service sedang dijalankan")
        serviceScope.launch {
            delay(3000L)
            stopSelf()
            Log.d(TAG, "Service dihentikan")
        }
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceJob.cancel()
        Log.d(TAG, "onDestroy: ")
    }
}