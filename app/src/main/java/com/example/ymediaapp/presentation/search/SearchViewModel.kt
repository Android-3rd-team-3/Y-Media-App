package com.example.ymediaapp.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.ymediaapp.data.repository.SearchRepositoryImpl
import com.example.ymediaapp.network.RetrofitClient
import com.example.ymediaapp.presentation.entity.SearchVideoEntity
import com.example.ymediaapp.presentation.repository.SearchRepository
import kotlinx.coroutines.launch


class SearchViewModel ( private val repository: SearchRepository): ViewModel() {
    private val _getSearchList= MutableLiveData<List<SearchVideoEntity>>()
    val searchList: LiveData<List<SearchVideoEntity>> get() = _getSearchList

    fun getSearchList() = viewModelScope.launch {
     // 데이터 베이스에서 받아오게 만들기
        _getSearchList.value = repository.getSearchList().items
    }
}

class SearchViewModelFactory: ViewModelProvider.Factory{
    private val repository = SearchRepositoryImpl(RetrofitClient.youtubeService)

    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras
    ): T {
        return SearchViewModel(repository) as T
    }
}