package com.a6tak.practice.ui

import com.a6tak.practice.db.NewsEntity
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNews(): Flow<List<NewsEntity>>
    suspend fun addNew(newsEntity: NewsEntity)
}