package com.example.bootcounter

import android.Manifest.permission.RECEIVE_BOOT_COMPLETED
import android.app.job.JobInfo
import android.content.ComponentName
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.bootcounter.broadcastReceiver.BootBroadCastReceiver
import com.example.bootcounter.domain.data.BootTimeStorage
import com.example.bootcounter.jobScheduler.PeriodicNotificationJob
import com.example.bootcounter.viewModel.CounterViewModel
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: CounterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        registerReceiver(
            BootBroadCastReceiver(),
            IntentFilter(RECEIVE_BOOT_COMPLETED)
        )

        viewModel = ViewModelProviders.of(this).get(CounterViewModel::class.java)
        viewModel.onCreate(this)
    }

    override fun onStart() {
        super.onStart()
        JobInfo.Builder(PERIODIC_NOTIFICATION_JOB_ID, ComponentName(this, PeriodicNotificationJob::class.java))
            .setPersisted(true)
            .setPeriodic(TimeUnit.MINUTES.toMillis(NOTIFICATION_INTERVAL_MINUTES))
            .build()
    }

    companion object {
        const val PERIODIC_NOTIFICATION_JOB_ID = 1
        const val NOTIFICATION_INTERVAL_MINUTES: Long = 15
    }
}
