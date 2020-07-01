package com.debin.localnews.repository

import androidx.lifecycle.LiveData
import com.debin.localnews.datasource.NewsDao
import com.debin.localnews.model.NewsData

class NewsDataRepository(private val newsDao: NewsDao) {

    val topNews : LiveData<List<NewsData>> = newsDao.getTopNews()
    val cityNews : LiveData<List<NewsData>> = newsDao.getCityNews()
    val countyNews : LiveData<List<NewsData>> = newsDao.getCountyNews()

}