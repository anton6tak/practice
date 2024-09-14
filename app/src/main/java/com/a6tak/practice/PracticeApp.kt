package com.a6tak.practice

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
//import android.content.Context
import android.os.Build
import androidx.core.content.ContextCompat
import androidx.room.Room
import com.a6tak.practice.db.AppDatabase
import com.a6tak.practice.ui.Repository

const val CHANNEL_ID = "channel_id_123"

class PracticeApp : Application() {
    private lateinit var database: AppDatabase //
    lateinit var repo: Repository

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        database = Room.databaseBuilder(this, AppDatabase::class.java, "app_db").build()
        repo = Repository(database)

    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is not in the Support Library.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system.
            val notificationManager: NotificationManager =
                ContextCompat.getSystemService(this, NotificationManager::class.java) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

}