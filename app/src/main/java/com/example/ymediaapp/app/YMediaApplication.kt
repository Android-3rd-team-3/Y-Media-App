package com.example.ymediaapp.app

import android.app.Application

class YMediaApplication: Application() {

    val appContainer = AppContainer()

    override fun onCreate() {
        INSTANCE = this
        super.onCreate()
    }

    companion object{
        private var INSTANCE: YMediaApplication? = null

        fun getInstance() = INSTANCE
    }

}