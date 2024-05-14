package com.example.ymediaapp.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.ymediaapp.data.repository.SearchRepositoryImpl
import com.example.ymediaapp.presentation.entity.YoutubeVideoEntity
import com.example.ymediaapp.presentation.repository.SearchRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: SearchRepository): ViewModel() {
    private val _getPopularList= MutableLiveData<List<YoutubeVideoEntity>>()
    val popularList: LiveData<List<YoutubeVideoEntity>> get() = _getPopularList

    private val _getCategoryList= MutableLiveData<List<YoutubeVideoEntity>>()
    val categoryList: LiveData<List<YoutubeVideoEntity>> get() = _getCategoryList

    //TODO Channel List에서 사용하는 Entity 변경필요

    private val _getCategoryChannelList= MutableLiveData<List<YoutubeVideoEntity>>()
    val categoryChannelList: LiveData<List<YoutubeVideoEntity>> get() = _getCategoryChannelList

    private fun getPopularList() = viewModelScope.launch {
        // 데이터베이스에서 받아오게(API 요청 part: snippet, chart: mostPopular)
        _getPopularList.value = listOf(
            YoutubeVideoEntity(
                thumbnail = "https://i.ytimg.com/vi/phuiiNCxRMg/default.jpg",
                name = "aespa 에스파 'Supernova' MV",
                description = "lorem"
            ),
            YoutubeVideoEntity(
                thumbnail = "https://i.ytimg.com/vi/phuiiNCxRMg/default.jpg",
                name = "aespa 에스파 'Supernova' MV",
                description = ""
            ),
            YoutubeVideoEntity(
                thumbnail = "https://i.ytimg.com/vi/phuiiNCxRMg/default.jpg",
                name = "aespa 에스파 'Supernova' MV",
                description = ""
            ),
            YoutubeVideoEntity(
                thumbnail = "https://i.ytimg.com/vi/phuiiNCxRMg/default.jpg",
                name = "aespa 에스파 'Supernova' MV",
                description = ""
            )
        )
    }

    private fun getCategoryList() = viewModelScope.launch {
        // 데이터 베이스에서 받아오게 만들기
        _getCategoryList.value = listOf(
            YoutubeVideoEntity(
                thumbnail = "https://i.ytimg.com/vi/phuiiNCxRMg/default.jpg",
                name = "aespa 에스파 'Supernova' MV",
                description = "lorem"
            ),
            YoutubeVideoEntity(
                thumbnail = "https://i.ytimg.com/vi/phuiiNCxRMg/default.jpg",
                name = "aespa 에스파 'Supernova' MV",
                description = ""
            ),
            YoutubeVideoEntity(
                thumbnail = "https://i.ytimg.com/vi/phuiiNCxRMg/default.jpg",
                name = "aespa 에스파 'Supernova' MV",
                description = ""
            ),
            YoutubeVideoEntity(
                thumbnail = "https://i.ytimg.com/vi/phuiiNCxRMg/default.jpg",
                name = "aespa 에스파 'Supernova' MV",
                description = ""
            )
        )
    }

    private fun getCategoryChannelList() = viewModelScope.launch {
        _getCategoryChannelList.value = listOf(
            YoutubeVideoEntity(
                thumbnail = "https://i.ytimg.com/vi/phuiiNCxRMg/default.jpg",
                name = "aespa 에스파 'Supernova' MV",
                description = "lorem"
            ),
            YoutubeVideoEntity(
                thumbnail = "https://i.ytimg.com/vi/phuiiNCxRMg/default.jpg",
                name = "aespa 에스파 'Supernova' MV",
                description = ""
            ),
            YoutubeVideoEntity(
                thumbnail = "https://i.ytimg.com/vi/phuiiNCxRMg/default.jpg",
                name = "aespa 에스파 'Supernova' MV",
                description = ""
            ),
            YoutubeVideoEntity(
                thumbnail = "https://i.ytimg.com/vi/phuiiNCxRMg/default.jpg",
                name = "aespa 에스파 'Supernova' MV",
                description = ""
            )
        )
    }

    fun getLists(){
        getCategoryList()
        getPopularList()
        getCategoryChannelList()
    }
}

class HomeViewModelFactory: ViewModelProvider.Factory{
    private val repository = SearchRepositoryImpl()

    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras
    ): T {
        return HomeViewModel(repository) as T
    }
}