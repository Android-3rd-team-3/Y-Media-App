package com.example.ymediaapp.presentation.my_video

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.ymediaapp.domain.entity.YoutubeVideoEntity
import com.example.ymediaapp.domain.repository.VideoRepository
import com.example.ymediaapp.presentation.model.YoutubeVideoModel
import com.example.ymediaapp.presentation.model.toModel
import kotlinx.coroutines.launch

class MyVideoViewModel(
    private val videoRepository: VideoRepository
): ViewModel() {

    val favoriteList: LiveData<List<YoutubeVideoModel>> = videoRepository.getVideoData().map { list ->
        list.map {
            it.toModel()
        }
    }

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