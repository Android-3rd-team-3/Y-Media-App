package com.example.ymediaapp.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ymediaapp.presentation.entity.YoutubeVideoEntity
import com.example.ymediaapp.presentation.repository.SearchRepository
import com.example.ymediaapp.presentation.repository.VideoRepository
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: VideoRepository) : ViewModel() {

    private val _selectedItem = MutableLiveData<YoutubeVideoEntity>()
    val selectedItem: LiveData<YoutubeVideoEntity> get() = _selectedItem

    private val _shareItem = MutableLiveData<YoutubeVideoEntity?>()

    val shareItem: LiveData<YoutubeVideoEntity?> get() = _shareItem


    fun toggleLike() {
        if (selectedItem.value != null) {
            _selectedItem.value = selectedItem.value!!.copy(isLike = !selectedItem.value!!.isLike)
            viewModelScope.launch {
                repository.insertVideoData(selectedItem.value!!)
            }
        }
    }

    fun setSelectedItem(selectedItem: YoutubeVideoEntity) {
        _selectedItem.value = selectedItem
    }

    fun setShareItem(selectedItem: YoutubeVideoEntity?){
        _shareItem.value = selectedItem
    }

}