package com.example.ymediaapp.presentation.detail

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.ymediaapp.data.repository.SearchRepositoryImpl
import com.example.ymediaapp.data.repository.VideoRepositoryImpl
import com.example.ymediaapp.network.RetrofitClient
import com.example.ymediaapp.presentation.entity.YoutubeVideoEntity
import com.example.ymediaapp.presentation.home.HomeViewModel
import com.example.ymediaapp.presentation.repository.SearchRepository
import com.example.ymediaapp.presentation.repository.VideoRepository
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
//    init{
//        viewModelScope.launch(Dispatchers.IO) {
//            val videoList = repository.getVideoData()
//
//        }
//    }

}

class DetailViewModelFactory(private val repository: VideoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetailViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}