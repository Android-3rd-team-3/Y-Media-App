package com.example.ymediaapp.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ymediaapp.domain.repository.SearchRepository
import com.example.ymediaapp.presentation.model.CategoryModel
import com.example.ymediaapp.presentation.model.YoutubeChannelModel
import com.example.ymediaapp.presentation.model.YoutubeVideoModel
import com.example.ymediaapp.presentation.model.toModel
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: SearchRepository) : ViewModel() {
    private val _getPopularList = MutableLiveData<List<YoutubeVideoModel>>()
    val popularList: LiveData<List<YoutubeVideoModel>> get() = _getPopularList

    private val _getCategoryVideoList = MutableLiveData<List<YoutubeVideoModel>>()
    val categoryVideoList: LiveData<List<YoutubeVideoModel>> get() = _getCategoryVideoList

    private val _getCategoryChannelList = MutableLiveData<List<YoutubeChannelModel>>()
    val categoryChannelList: LiveData<List<YoutubeChannelModel>> get() = _getCategoryChannelList

    private val _getCategoryList = MutableLiveData<List<CategoryModel>>()

    val categoryList: LiveData<List<CategoryModel>> get() = _getCategoryList


    private val _getCurrentCategoryId = MutableLiveData<String?>()

    val currentCategoryId: LiveData<String?> get() = _getCurrentCategoryId

    fun getPopularList() = viewModelScope.launch {
        _getPopularList.value = try {
            repository.getPopularList().items.map { it.toModel() }
        } catch (e: Exception) {
            emptyList()
        }
    }

    fun getCategoryVideoList(categoryId: String = "0") = viewModelScope.launch {
        _getCategoryVideoList.value = try {
            repository.getVideoByCategoryList(categoryId).items.map { it.toModel() }
        } catch (e: Exception) {
            emptyList()
        }
    }

    fun getCategoryChannelList() = viewModelScope.launch {
        val channelIds = getChannelIds()
        _getCategoryChannelList.value = try {
            repository.getChannelByCategoryList(channelIds).items.map { it.toModel() }.sortedBy { it.channelId }
        } catch (e: Exception) {
            emptyList()
        }
    }

    fun getCategoryList() = viewModelScope.launch {
        _getCategoryList.value = try {
            repository.getCategoryList().items.filter { it.assignable }.map { it.toModel() }
        } catch (e: Exception) {
            emptyList()
        }
    }

    fun setCategoryId(id: String){
        _getCurrentCategoryId.value = id
    }

    private fun getChannelIds(): String {
        return try {
            (categoryVideoList.value ?: listOf()).joinToString(", ") { it.channelId }
        } catch (e: Exception) {
            ""
        }
    }

}

class HomeViewModelFactory(private val searchRepository: SearchRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(
                repository = searchRepository
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}