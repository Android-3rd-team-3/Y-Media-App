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

class DetailViewModel(private val repository: VideoRepository) : ViewModel() {

    private val _selectedItem = MutableLiveData<YoutubeVideoModel>()
    val selectedItem: LiveData<YoutubeVideoModel> get() = _selectedItem

    private val _shareItem = MutableLiveData<YoutubeVideoModel?>()

    val shareItem: LiveData<YoutubeVideoModel?> get() = _shareItem


    fun toggleLike() {
        if (selectedItem.value != null) {
            _selectedItem.value = selectedItem.value!!.copy(isLike = !selectedItem.value!!.isLike)
            viewModelScope.launch {
                repository.insertVideoData(selectedItem.value!!.toEntity())
            }
        }
    }

    fun setSelectedItem(selectedItem: YoutubeVideoModel) {
        viewModelScope.launch {
            val videoEntity = repository.getDataById(selectedItem.videoId)
            if (videoEntity == null) {
                _selectedItem.value = selectedItem
            } else {
                videoEntity?.let { _selectedItem.value = it.toModel() }
            }
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
