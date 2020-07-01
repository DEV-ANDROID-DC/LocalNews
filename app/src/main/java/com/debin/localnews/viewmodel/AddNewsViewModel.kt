package com.debin.localnews.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.debin.localnews.NewsApp
import com.debin.localnews.datasource.NewsDatabase
import com.debin.localnews.model.NewsData
import com.debin.localnews.repository.AddNewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNewsViewModel() : ViewModel() {

    private val addNewsRepository : AddNewsRepository

    init {
        val newsDao = NewsDatabase.getDatabase(NewsApp.instance, viewModelScope).NewsDao()
        addNewsRepository = AddNewsRepository(newsDao)
    }

    fun insertNews(newsData: NewsData) = viewModelScope.launch (Dispatchers.IO){
        addNewsRepository.insertNews(newsData)
    }
}