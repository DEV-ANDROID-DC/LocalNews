package com.debin.localnews.datasource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.debin.localnews.model.NewsData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [NewsData::class], version = 1)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun NewsDao() : NewsDao

    companion object {
        //meaning that writes to this field
        // are immediately made visible to other threads.
        @Volatile
        private var INSTANCE : NewsDatabase? = null

        fun getDatabase(
            context: Context,
            scope : CoroutineScope
        ) : NewsDatabase {
            return INSTANCE?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NewsDatabase::class.java,
                    "news_database"
                )
                    .fallbackToDestructiveMigration() //
                    .addCallback(NewsDatabaseCallback(scope))
                    .build()

                INSTANCE = instance

                instance
            }
        }

        private class NewsDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback(){

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)

                INSTANCE?.let{database ->
                    //without blocking current thread
                    scope.launch { Dispatchers.IO }
                    // need to add code for delete the database
                    //populateDatabse(database.NewsDao())
                }
            }
        }

        fun populateDatabse(newsDao: NewsDao) {

            newsDao.deleteAll()
        }


    }
}