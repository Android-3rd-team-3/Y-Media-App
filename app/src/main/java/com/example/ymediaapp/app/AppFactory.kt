package com.example.ymediaapp.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.ymediaapp.app.network.RetrofitClient
import com.example.ymediaapp.data.repository.SearchRepositoryImpl
import com.example.ymediaapp.domain.repository.SearchRepository
import com.example.ymediaapp.domain.repository.VideoRepository
import com.example.ymediaapp.presentation.home.HomeViewModel
import com.example.ymediaapp.presentation.my_video.MyVideoViewModel

interface Factory<T> {
    fun create(): T
}

class MyVideoViewModelFactory(private val videoRepository: VideoRepository) : Factory<MyVideoViewModel> {
    override fun create(): MyVideoViewModel {
        return MyVideoViewModel(videoRepository)
    }
}

class HomeViewModelFactory(private val searchRepository: SearchRepository) :Factory<HomeViewModel> {
    override fun create(): HomeViewModel {
        return HomeViewModel(searchRepository)
    }

}