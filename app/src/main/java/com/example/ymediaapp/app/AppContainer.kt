package com.example.ymediaapp.app

import android.content.Context
import com.example.ymediaapp.app.network.RetrofitClient
import com.example.ymediaapp.data.repository.SearchRepositoryImpl
import com.example.ymediaapp.data.repository.VideoRepositoryImpl

class AppContainer(context: Context) {
    private val youtubeService = RetrofitClient.youtubeService

    private val searchRepository = SearchRepositoryImpl(youtubeService)
    private val videoRepository = VideoRepositoryImpl(context)

    val myVideoViewModelFactory = MyVideoViewModelFactory(videoRepository)
    val homeViewModelFactory = HomeViewModelFactory(searchRepository)

    val detailViewModelFactory = DetailViewModelFactory(videoRepository)
}

