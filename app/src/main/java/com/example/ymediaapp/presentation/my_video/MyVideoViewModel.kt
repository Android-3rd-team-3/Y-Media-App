package com.example.ymediaapp.presentation.my_video

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.map
import com.example.ymediaapp.domain.repository.VideoRepository
import com.example.ymediaapp.presentation.model.YoutubeVideoModel
import com.example.ymediaapp.presentation.model.toModel
import java.io.IOException

class MyVideoViewModel(
    videoRepository: VideoRepository
): ViewModel() {

    val favoriteList: LiveData<List<YoutubeVideoModel>> = try {
        videoRepository.getVideoData().map { list ->
            list.map {
                it.toModel()
            }
        }
    } catch (e: IOException) {
        MutableLiveData(emptyList())
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