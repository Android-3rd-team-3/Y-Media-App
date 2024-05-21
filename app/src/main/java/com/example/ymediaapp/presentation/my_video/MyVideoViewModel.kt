package com.example.ymediaapp.presentation.my_video

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ymediaapp.domain.entity.YoutubeVideoEntity
import com.example.ymediaapp.domain.repository.VideoRepository
import kotlinx.coroutines.launch

class MyVideoViewModel(
    private val videoRepository: VideoRepository
): ViewModel() {

    val favoriteList: LiveData<List<YoutubeVideoEntity>> = videoRepository.getVideoData()

    fun addItem(item: YoutubeVideoEntity) = viewModelScope.launch {
        videoRepository.insertVideoData(item)
    }
}

class MyVideoViewModelFactory(private val videoRepository: VideoRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MyVideoViewModel::class.java)) {
            return MyVideoViewModel(
                videoRepository = videoRepository
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}