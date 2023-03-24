package com.example.bootcounter.broadcastReceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.bootcounter.domain.data.BootTimeStorage

class BootBroadCastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("[STARTUP DONE]", "starting app")
        if (Intent.ACTION_BOOT_COMPLETED == intent!!.action) {
            BootTimeStorage.saveNewBootTime()
        }
    }
}