package com.a6tak.practice.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database([NewsEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}