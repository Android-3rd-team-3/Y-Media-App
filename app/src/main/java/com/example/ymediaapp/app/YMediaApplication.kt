package com.example.ymediaapp.app

import android.app.Application

class YMediaApplication: Application() {

    val appContainer = AppContainer(this)
}