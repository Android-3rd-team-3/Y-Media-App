package com.example.ymediaapp.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ymediaapp.domain.repository.VideoRepository
import com.example.ymediaapp.presentation.model.YoutubeVideoModel
import com.example.ymediaapp.presentation.model.toEntity
import com.example.ymediaapp.presentation.model.toModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.selects.select

class DetailViewModel(private val repository: VideoRepository) : ViewModel() {

    private val _selectedItem = MutableLiveData<YoutubeVideoModel>()
    val selectedItem: LiveData<YoutubeVideoModel> get() = _selectedItem

    private val _shareItem = MutableLiveData<YoutubeVideoModel?>()

    val shareItem: LiveData<YoutubeVideoModel?> get() = _shareItem

    private val _isLikeStatus = MutableLiveData<Boolean>()
    val isLikeStatus: LiveData<Boolean> get() = _isLikeStatus

    fun toggleLike() {
        selectedItem.value?.let { item ->
            viewModelScope.launch {
                val videoEntity = repository.getDataById(item.videoId)
                if (videoEntity == null) {
                    repository.insertVideoData(item.toEntity())
                    _isLikeStatus.postValue(true)
                } else {
                    repository.deleteVideoData(item.toEntity())
                    _isLikeStatus.postValue(false)
                }
            }
        }
    }

    fun setSelectedItem(selectedItem: YoutubeVideoModel) {
        _selectedItem.value = selectedItem
        viewModelScope.launch {
            val videoEntity = repository.getDataById(selectedItem.videoId)
            _isLikeStatus.postValue(videoEntity != null)
        }
    }

    fun setShareItem(selectedItem: YoutubeVideoModel?) {
        _shareItem.value = selectedItem
    }

    fun shareItem() {
        _shareItem.value = _selectedItem.value
    }
}

class DetailViewModelFactory(private val videoRepository: VideoRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(
                repository = videoRepository
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}
