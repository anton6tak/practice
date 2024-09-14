package com.a6tak.practice.utils

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class PracticeWorker(
    private val appContext: Context,
    workerParams: WorkerParameters
) : Worker(appContext, workerParams) {
    override fun doWork(): Result {

        runBlocking {
            delay(3000)
        }
        val builder = notificationBuilder(appContext, "hello!", "from Work Manger!")
        showNotification(builder, appContext)

        return Result.success()
    }
}