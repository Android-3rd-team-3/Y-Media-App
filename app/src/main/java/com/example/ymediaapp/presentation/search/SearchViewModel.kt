package com.example.ymediaapp.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.ymediaapp.data.repository.SearchRepositoryImpl
import com.example.ymediaapp.app.network.RetrofitClient
import com.example.ymediaapp.domain.entity.SearchVideoEntity
import com.example.ymediaapp.domain.repository.SearchRepository
import com.example.ymediaapp.presentation.model.SearchVideoModel
import com.example.ymediaapp.presentation.model.YoutubeVideoModel
import com.example.ymediaapp.presentation.model.toModel
import kotlinx.coroutines.launch


class SearchViewModel ( private val searchRepository: SearchRepository): ViewModel() {
    private val _getSearchList= MutableLiveData<List<SearchVideoModel>>()
    val searchList: LiveData<List<SearchVideoModel>> get() = _getSearchList

    private val _getVideoById = MutableLiveData<YoutubeVideoModel>()
    val videoById: LiveData<YoutubeVideoModel> get() = _getVideoById

    fun getSearchList(query: String) = viewModelScope.launch {
        _getSearchList.value = searchRepository.getSearchList(query).items.map { it.toModel() }
    }

    fun getVideoById(id: String) = viewModelScope.launch {
        _getVideoById.value = searchRepository.getVideoById(id).items[0].toModel()
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