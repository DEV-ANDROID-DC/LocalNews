package com.debin.localnews.repository

import androidx.annotation.WorkerThread
import com.debin.localnews.datasource.NewsDao
import com.debin.localnews.model.NewsData

class AddNewsRepository(private val newsDao: NewsDao) {

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertNews(newsData: NewsData) {
        newsDao.insertNews(newsData)
    }
}