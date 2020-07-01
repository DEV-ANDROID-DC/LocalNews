package com.debin.localnews

import android.app.Application

class NewsApp : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: NewsApp
            private set
    }
}
