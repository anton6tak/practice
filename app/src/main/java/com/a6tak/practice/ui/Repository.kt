package com.a6tak.practice.ui

import com.a6tak.practice.db.AppDatabase
import com.a6tak.practice.db.NewsEntity
import kotlinx.coroutines.flow.Flow

class Repository(private val db: AppDatabase): NewsRepository {
    private val newsDao = db.newsDao()

    override fun getNews(): Flow<List<NewsEntity>> {
        return newsDao.getAll()
    }

    override suspend fun addNew(newsEntity: NewsEntity) {
        return newsDao.insertAll(newsEntity)
    }
}