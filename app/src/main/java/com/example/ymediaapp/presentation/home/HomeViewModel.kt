package com.example.ymediaapp.presentation.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ymediaapp.domain.entity.CategoryEntity
import com.example.ymediaapp.domain.entity.YoutubeChannelEntity
import com.example.ymediaapp.domain.entity.YoutubeVideoEntity
import com.example.ymediaapp.domain.repository.SearchRepository
import kotlinx.coroutines.launch
import java.lang.StringBuilder

class HomeViewModel(private val repository: SearchRepository) : ViewModel() {
    private val _getPopularList = MutableLiveData<List<YoutubeVideoEntity>>()
    val popularList: LiveData<List<YoutubeVideoEntity>> get() = _getPopularList

    private val _getCategoryVideoList = MutableLiveData<List<YoutubeVideoEntity>>()
    val categoryVideoList: LiveData<List<YoutubeVideoEntity>> get() = _getCategoryVideoList

    private val _getCategoryChannelList = MutableLiveData<List<YoutubeChannelEntity>>()
    val categoryChannelList: LiveData<List<YoutubeChannelEntity>> get() = _getCategoryChannelList

    private val _getCategoryList = MutableLiveData<List<CategoryEntity>>()

    val categoryList: LiveData<List<CategoryEntity>> get() = _getCategoryList

    fun getPopularList() = viewModelScope.launch {
        // 데이터베이스에서 받아오게(API 요청 part: snippet, chart: mostPopular)
        _getPopularList.value = repository.getPopularList().items
    }

    fun getCategoryVideoList(categoryId: String = "0") = viewModelScope.launch {
        // 데이터 베이스에서 받아오게 만들기
        _getCategoryVideoList.value = repository.getVideoByCategoryList(categoryId).items
    }

    fun getCategoryChannelList() = viewModelScope.launch {
        val channelIds = getChannelIds()
        _getCategoryChannelList.value = repository.getChannelByCategoryList(channelIds).items
    }

    fun getCategoryList() = viewModelScope.launch {
        _getCategoryList.value = repository.getCategoryList().items.filter { it.assignable }
    }

    private fun getChannelIds(): String {
        val sb = StringBuilder()
        (categoryVideoList.value ?: listOf()).forEach {
            sb.append(it.channelId)
            sb.append(", ")
        }
        Log.d("string", sb.toString())
        if (sb.lastIndex > 0) sb.deleteAt(sb.lastIndex)
        return sb.toString()
    }
}