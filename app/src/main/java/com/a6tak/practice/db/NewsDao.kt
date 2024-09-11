package com.a6tak.practice.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Query("SELECT * FROM news")
    fun getAll(): Flow<List<NewsEntity>>

    @Insert
    suspend fun insertAll(vararg newsEntity: NewsEntity)

    @Query("DELETE FROM news")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(newsEntity: NewsEntity)
}