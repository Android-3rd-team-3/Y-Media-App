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

    private var _favoriteList = MutableLiveData<List<YoutubeVideoEntity>>()
    val favoriteList: LiveData<List<YoutubeVideoEntity>> get() = _favoriteList

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val list = videoRepository.getVideoData()
            _favoriteList.postValue(list)
        }
    }
}

class MyVideoViewModelFactory: ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MyVideoViewModel::class.java)) {
            return MyVideoViewModel(
                YMediaApplication.getInstance().provideVideoRepository()
            ) as T
        }
        throw IllegalArgumentException("Unknown Class")
    }
}