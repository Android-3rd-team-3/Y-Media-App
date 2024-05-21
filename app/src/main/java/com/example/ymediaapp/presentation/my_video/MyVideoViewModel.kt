package com.example.ymediaapp.presentation.my_video

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ymediaapp.app.YMediaApplication
import com.example.ymediaapp.data.repository.VideoRepositoryImpl
import com.example.ymediaapp.domain.entity.YoutubeVideoEntity
import com.example.ymediaapp.domain.repository.VideoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyVideoViewModel(
    private val videoRepository: VideoRepository
): ViewModel() {

    val favoriteList: LiveData<List<YoutubeVideoEntity>> = videoRepository.getVideoData()

}