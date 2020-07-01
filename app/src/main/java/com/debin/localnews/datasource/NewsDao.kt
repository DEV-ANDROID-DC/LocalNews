package com.debin.localnews.datasource

import androidx.lifecycle.LiveData
import androidx.room.*
import com.debin.localnews.model.NewsData

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(newsItems : NewsData)

    @Query("DELETE from news_table")
    fun deleteAll()

    @Delete
    fun deleteNews(newsItems: NewsData)

    @Query("SELECT * from news_table where cat_id = 1")
    fun getTopNews() : LiveData<List<NewsData>>

    @Query("SELECT * from news_table where cat_id = 2")
    fun getCityNews() : LiveData<List<NewsData>>

    @Query("SELECT * from news_table where cat_id = 3")
    fun getCountyNews() : LiveData<List<NewsData>>

}