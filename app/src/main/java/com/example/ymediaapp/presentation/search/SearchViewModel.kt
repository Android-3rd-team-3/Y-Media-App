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
import com.example.ymediaapp.domain.repository.VideoRepository
import kotlinx.coroutines.launch


class SearchViewModel ( private val searchRepository: SearchRepository): ViewModel() {
    private val _getSearchList= MutableLiveData<List<SearchVideoEntity>>()
    val searchList: LiveData<List<SearchVideoEntity>> get() = _getSearchList

    fun getSearchList(query: String) = viewModelScope.launch {
        _getSearchList.value = searchRepository.getSearchList(query).items
    }
}

//class SearchViewModelFactory(private val searchRepository: SearchRepository): ViewModelProvider.Factory{
//    private val repository = SearchRepositoryImpl(RetrofitClient.youtubeService)
//
//    override fun <T : ViewModel> create(
//        modelClass: Class<T>,
//        extras: CreationExtras
//    ): T {
//        return SearchViewModel(repository) as T
//    }
//}
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