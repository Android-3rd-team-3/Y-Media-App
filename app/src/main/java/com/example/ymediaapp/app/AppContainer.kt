package com.example.ymediaapp.app

import android.content.Context
import com.example.ymediaapp.app.network.RetrofitClient
import com.example.ymediaapp.data.repository.SearchRepositoryImpl
import com.example.ymediaapp.data.repository.VideoRepositoryImpl
import com.example.ymediaapp.domain.repository.VideoRepository
import com.example.ymediaapp.presentation.my_video.MyVideoViewModelFactory

class AppContainer(context: Context) {
    private val youtubeService = RetrofitClient.youtubeService

    val searchRepository = SearchRepositoryImpl(youtubeService)
    val videoRepository = VideoRepositoryImpl(context)

    var myVideoContainer: MyVideoContainer? = null

}

class MyVideoContainer(
    private val videoRepository: VideoRepository
) {
    val myVideoViewModelFactory = MyVideoViewModelFactory(videoRepository)
    val user = DummyAuth.getUser()
    }

//val homeViewModelFactory = HomeViewModelFactory(searchRepository)
//    val detailViewModelFactory = DetailViewModelFactory(videoRepository)

