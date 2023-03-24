package com.example.bootcounter

import android.Manifest.permission.RECEIVE_BOOT_COMPLETED
import android.app.job.JobInfo
import android.content.ComponentName
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.bootcounter.broadcastReceiver.BootBroadCastReceiver
import com.example.bootcounter.domain.data.BootTimeStorage
import com.example.bootcounter.jobScheduler.PeriodicNotificationJob
import com.example.bootcounter.service.PeriodicNotificationService
import com.example.bootcounter.viewModel.CounterViewModel
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    private val viewModel: CounterViewModel by viewModels()
    private val bootEventReceiver = BootBroadCastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        registerReceiver(
            bootEventReceiver,
            IntentFilter(RECEIVE_BOOT_COMPLETED)
        )

        viewModel.onCreate(this)
    }

    override fun onStart() {
        super.onStart()
        JobInfo.Builder(PERIODIC_NOTIFICATION_JOB_ID, ComponentName(this, PeriodicNotificationJob::class.java))
            .setPersisted(true)
            .setPeriodic(TimeUnit.MINUTES.toMillis(NOTIFICATION_INTERVAL_MINUTES))
            .build()

        startService(
            Intent(
                this,
                PeriodicNotificationService::class.java
            )
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        registerReceiver(
            bootEventReceiver,
            IntentFilter(RECEIVE_BOOT_COMPLETED)
        )
    }

    companion object {
        const val PERIODIC_NOTIFICATION_JOB_ID = 1
        const val NOTIFICATION_INTERVAL_MINUTES: Long = 15
    }
}
