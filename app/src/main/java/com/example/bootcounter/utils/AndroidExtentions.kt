package com.example.bootcounter.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.example.bootcounter.MainActivity
import com.example.bootcounter.R
import com.example.bootcounter.domain.data.model.BootTimeModel

fun Service.publishNotification(bootTimeModel: BootTimeModel) {

    Log.d("PublishNotification", bootTimeModel.toString())

    val mBuilder = NotificationCompat.Builder(this.applicationContext, "boot_count_notify")
    val intent = Intent(this.applicationContext, MainActivity::class.java)
    val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

    val notificationLayout = RemoteViews(packageName, R.layout.notification)
    notificationLayout.setTextViewText(R.id.title_text, bootTimeModel.orderNumber.toString())
    notificationLayout.setTextViewText(R.id.price_text, bootTimeModel.timestamp.toString())

    mBuilder.setContentIntent(pendingIntent)
    mBuilder.setCustomContentView(notificationLayout)
    mBuilder.setDefaults(NotificationCompat.DEFAULT_VIBRATE or NotificationCompat.DEFAULT_SOUND)
    mBuilder.setPriority(NotificationCompat.PRIORITY_MAX)

    val mNotificationManager =
        this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(
            "boot_count_notify",
            "Boot counter notification",
            NotificationManager.IMPORTANCE_HIGH
        )
        mNotificationManager.createNotificationChannel(channel)
    }
    mNotificationManager.notify(0, mBuilder.build())
}

fun String.isAllDigits(): Boolean {
    forEach { if (!it.isDigit()) return false }
    return true
}