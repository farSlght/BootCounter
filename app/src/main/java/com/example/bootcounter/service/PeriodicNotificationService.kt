package com.example.bootcounter.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.example.bootcounter.domain.data.BootTimeStorage
import com.example.bootcounter.domain.data.model.BootTimeModel
import com.example.bootcounter.utils.publishNotification
import kotlinx.coroutines.*

class PeriodicNotificationService : Service() {

    private val storage = BootTimeStorage

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e("12345", "onStartCommand")
        val bootTimes = storage.getLatestBootTimeDiff()
        publishNotification(bootTimes)
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }
}
