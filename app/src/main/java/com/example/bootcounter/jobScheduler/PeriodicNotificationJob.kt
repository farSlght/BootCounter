package com.example.bootcounter.jobScheduler

import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent
import com.example.bootcounter.service.PeriodicNotificationService


class PeriodicNotificationJob : JobService() {
    override fun onStartJob(params: JobParameters): Boolean {
        startService(
            Intent(
                this,
                PeriodicNotificationService::class.java
            )
        )
        return true
    }

    override fun onStopJob(params: JobParameters): Boolean {
        return true
    }

}