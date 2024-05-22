package com.example.ymediaapp.app

import com.example.ymediaapp.app.network.RetrofitClient
import com.example.ymediaapp.data.repository.SearchRepositoryImpl
import com.example.ymediaapp.data.repository.VideoRepositoryImpl
import com.example.ymediaapp.domain.repository.SearchRepository
import com.example.ymediaapp.domain.repository.VideoRepository
import com.example.ymediaapp.presentation.detail.DetailViewModelFactory
import com.example.ymediaapp.presentation.home.HomeViewModelFactory
import com.example.ymediaapp.presentation.my_video.MyVideoViewModelFactory
import com.example.ymediaapp.presentation.search.SearchViewModelFactory

class AppContainer() {
    private val youtubeService = RetrofitClient.youtubeService

    val searchRepository = SearchRepositoryImpl(youtubeService)
    val videoRepository: VideoRepository by lazy {
        VideoRepositoryImpl(YMediaApplication.getInstance()!!)
    }

    var myVideoContainer: MyVideoContainer? = null
    var searchContainer: SearchContainer? = null
    var homeContainer: HomeContainer? = null
    var detailContainer: DetailContainer? = null

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
}


class HomeContainer(
    private val searchRepository: SearchRepository
) {
    val homeViewModelFactory = HomeViewModelFactory(searchRepository)
}
class DetailContainer(private val videoRepository: VideoRepository){
    val detailViewModelFactory = DetailViewModelFactory(videoRepository)
}