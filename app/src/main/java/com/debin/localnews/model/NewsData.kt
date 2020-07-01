package com.debin.localnews.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_table")
data class NewsData (
    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name = "news_id")
    var id : Int,

    @ColumnInfo(name = "news_title")
    var title : String,

    @ColumnInfo(name = "news_des")
    var description : String,

    @ColumnInfo(name = "news_reporter")
    var reporter : String,

    @ColumnInfo(name = "cat_id")
    var categoryId : String
)








