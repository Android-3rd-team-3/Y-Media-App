package com.example.ymediaapp.app

import com.example.ymediaapp.domain.repository.VideoRepository
import com.example.ymediaapp.presentation.my_video.MyVideoViewModel

interface Factory<T> {
    fun create(): T
}

class MyVideoViewModelFactory(private val videoRepository: VideoRepository) : Factory<MyVideoViewModel> {
    override fun create(): MyVideoViewModel {
        return MyVideoViewModel(videoRepository)
    }
}