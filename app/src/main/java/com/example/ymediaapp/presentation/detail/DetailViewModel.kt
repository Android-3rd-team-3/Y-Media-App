package com.example.ymediaapp.presentation.detail

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ymediaapp.app.YMediaApplication
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.ymediaapp.data.repository.SearchRepositoryImpl
import com.example.ymediaapp.data.repository.VideoRepositoryImpl
import com.example.ymediaapp.domain.entity.YoutubeVideoEntity
import com.example.ymediaapp.domain.repository.VideoRepository
import com.example.ymediaapp.app.network.RetrofitClient
import com.example.ymediaapp.presentation.home.HomeViewModel
import com.example.ymediaapp.domain.repository.SearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

    fun setShareItem(selectedItem: YoutubeVideoEntity?) {
        _shareItem.value = selectedItem
    }

}