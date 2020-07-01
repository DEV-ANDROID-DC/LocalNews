package com.debin.localnews.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.debin.localnews.NewsApp
import com.debin.localnews.datasource.NewsDatabase
import com.debin.localnews.model.NewsData
import com.debin.localnews.repository.NewsDataRepository

class NewsDataViewModel () : ViewModel() {

    private val newsDataRepository : NewsDataRepository
    val topNews : LiveData<List<NewsData>>
    val cityNews : LiveData<List<NewsData>>
    val countyNews : LiveData<List<NewsData>>

    init {
        val newsDao = NewsDatabase.getDatabase(NewsApp.instance, viewModelScope).NewsDao()
        newsDataRepository = NewsDataRepository(newsDao)

        topNews = newsDataRepository.topNews
        cityNews = newsDataRepository.cityNews
        countyNews = newsDataRepository.countyNews
    }
}