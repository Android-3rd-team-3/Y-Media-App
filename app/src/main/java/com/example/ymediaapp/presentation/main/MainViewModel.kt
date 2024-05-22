package com.example.ymediaapp.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ymediaapp.domain.entity.SearchVideoEntity
import com.example.ymediaapp.presentation.model.SearchVideoModel
import com.example.ymediaapp.presentation.model.YoutubeVideoModel

class MainViewModel : ViewModel() {
    private val _selectedItem = MutableLiveData<YoutubeVideoModel?>()
    val selectedItem: LiveData<YoutubeVideoModel?> get() = _selectedItem


    fun setSelectedItem(selectedItem: YoutubeVideoModel?) {
        _selectedItem.value = selectedItem
    }
}