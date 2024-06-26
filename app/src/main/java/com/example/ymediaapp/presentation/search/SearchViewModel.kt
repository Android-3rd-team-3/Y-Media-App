package com.example.ymediaapp.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ymediaapp.domain.repository.SearchRepository
import com.example.ymediaapp.presentation.model.SearchVideoModel
import com.example.ymediaapp.presentation.model.YoutubeVideoModel
import com.example.ymediaapp.presentation.model.toModel
import kotlinx.coroutines.launch


class SearchViewModel ( private val searchRepository: SearchRepository): ViewModel() {
    private val _getSearchList= MutableLiveData<List<SearchVideoModel>>()
    val searchList: LiveData<List<SearchVideoModel>> get() = _getSearchList

    private val _getVideoById = MutableLiveData<YoutubeVideoModel?>()
    val videoById: LiveData<YoutubeVideoModel?> get() = _getVideoById

    fun getSearchList(query: String) = viewModelScope.launch {
        _getSearchList.value = try {
            searchRepository.getSearchList(query).items.map { it.toModel() }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    fun getVideoById(id: String) = viewModelScope.launch {
        _getVideoById.value = try {
            searchRepository.getVideoById(id).items[0].toModel()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun clearVideoById() {
        _getVideoById.value = null
    }
}

class SearchViewModelFactory(private val searchRepository: SearchRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return SearchViewModel(
                searchRepository = searchRepository
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}