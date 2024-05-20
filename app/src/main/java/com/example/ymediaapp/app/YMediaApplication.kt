package com.example.ymediaapp.app

import android.app.Application
import com.example.ymediaapp.app.network.RetrofitClient
import com.example.ymediaapp.data.repository.SearchRepositoryImpl
import com.example.ymediaapp.data.repository.VideoRepositoryImpl

class YMediaApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {
        private var appInstance: YMediaApplication? = null

        fun getInstance(): YMediaApplication {
            return appInstance ?: throw IllegalArgumentException("None Application")
        }
    }

    fun provideSearchRepository() = SearchRepositoryImpl(RetrofitClient.youtubeService)
    fun provideVideoRepository() = VideoRepositoryImpl(this)
}