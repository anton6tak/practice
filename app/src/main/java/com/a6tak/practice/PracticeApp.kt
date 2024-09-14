package com.a6tak.practice

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.a6tak.practice.db.AppDatabase
import com.a6tak.practice.ui.Repository

class PracticeApp : Application() {
    private lateinit var database: AppDatabase //
    lateinit var repo: Repository

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this, AppDatabase::class.java, "app_db").build()
        repo = Repository(database)
    }
}