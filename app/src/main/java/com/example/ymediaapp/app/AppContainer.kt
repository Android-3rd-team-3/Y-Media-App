package com.example.ymediaapp.app

import android.content.Context
import com.example.ymediaapp.app.network.RetrofitClient
import com.example.ymediaapp.data.repository.SearchRepositoryImpl
import com.example.ymediaapp.data.repository.VideoRepositoryImpl
import com.example.ymediaapp.domain.repository.SearchRepository
import com.example.ymediaapp.domain.repository.VideoRepository
import com.example.ymediaapp.presentation.my_video.MyVideoViewModelFactory
import com.example.ymediaapp.presentation.search.SearchViewModelFactory

class AppContainer(context: Context) {
    private val youtubeService = RetrofitClient.youtubeService

    val searchRepository = SearchRepositoryImpl(youtubeService)
    val videoRepository = VideoRepositoryImpl(context)

    var myVideoContainer: MyVideoContainer? = null
    var searchContainer: SearchContainer? = null

}

class MyVideoContainer(
    private val videoRepository: VideoRepository
) {
    val myVideoViewModelFactory = MyVideoViewModelFactory(videoRepository)
    val user = DummyAuth.getUser()
}

class SearchContainer(
    private val searchRepository: SearchRepository
) {
    val searchViewModelFactory = SearchViewModelFactory(searchRepository)
    val user = DummyAuth.getUser()
}


