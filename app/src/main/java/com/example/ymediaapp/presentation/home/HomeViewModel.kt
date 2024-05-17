package com.example.ymediaapp.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.ymediaapp.data.repository.SearchRepositoryImpl
import com.example.ymediaapp.network.RetrofitClient
import com.example.ymediaapp.presentation.entity.CategoryEntity
import com.example.ymediaapp.presentation.entity.YoutubeChannelEntity
import com.example.ymediaapp.presentation.entity.YoutubeVideoEntity
import com.example.ymediaapp.presentation.repository.SearchRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: SearchRepository): ViewModel() {
    private val _getPopularList= MutableLiveData<List<YoutubeVideoEntity>>()
    val popularList: LiveData<List<YoutubeVideoEntity>> get() = _getPopularList

    private val _getCategoryVideoList= MutableLiveData<List<YoutubeVideoEntity>>()
    val categoryVideoList: LiveData<List<YoutubeVideoEntity>> get() = _getCategoryVideoList

    private val _getCategoryChannelList= MutableLiveData<List<YoutubeChannelEntity>>()
    val categoryChannelList: LiveData<List<YoutubeChannelEntity>> get() = _getCategoryChannelList

    private val _getCategoryList = MutableLiveData<List<CategoryEntity>>()

    val categoryList: LiveData<List<CategoryEntity>> get() = _getCategoryList

    private fun getPopularList() = viewModelScope.launch {
        // 데이터베이스에서 받아오게(API 요청 part: snippet, chart: mostPopular)
        _getPopularList.value = listOf(
            YoutubeVideoEntity(
                thumbnail = "https://i.ytimg.com/vi/phuiiNCxRMg/default.jpg",
                name = "aespa 에스파 'Supernova' MV",
                videoId = "",
                channelId = "",
                description = "lorem"
            ),
            YoutubeVideoEntity(
                thumbnail = "https://i.ytimg.com/vi/phuiiNCxRMg/default.jpg",
                name = "aespa 에스파 'Supernova' MV",
                videoId = "",
                channelId = "",
                description = "lorem"
            ),
            YoutubeVideoEntity(
                thumbnail = "https://i.ytimg.com/vi/phuiiNCxRMg/default.jpg",
                name = "aespa 에스파 'Supernova' MV",
                videoId = "",
                channelId = "",
                description = "lorem"
            ),
            YoutubeVideoEntity(
                thumbnail = "https://i.ytimg.com/vi/phuiiNCxRMg/default.jpg",
                name = "aespa 에스파 'Supernova' MV",
                videoId = "",
                channelId = "",
                description = "lorem"
            ),
        )
    }

    private fun getCategoryList() = viewModelScope.launch {
        // 데이터 베이스에서 받아오게 만들기
        _getCategoryVideoList.value = listOf(
            YoutubeVideoEntity(
                thumbnail = "https://i.ytimg.com/vi/phuiiNCxRMg/default.jpg",
                name = "aespa 에스파 'Supernova' MV",
                videoId = "",
                channelId = "",
                description = "lorem"
            ),
            YoutubeVideoEntity(
                thumbnail = "https://i.ytimg.com/vi/phuiiNCxRMg/default.jpg",
                name = "aespa 에스파 'Supernova' MV",
                videoId = "",
                channelId = "",
                description = "lorem"
            ),
            YoutubeVideoEntity(
                thumbnail = "https://i.ytimg.com/vi/phuiiNCxRMg/default.jpg",
                name = "aespa 에스파 'Supernova' MV",
                videoId = "",
                channelId = "",
                description = "lorem"
            ),
            YoutubeVideoEntity(
                thumbnail = "https://i.ytimg.com/vi/phuiiNCxRMg/default.jpg",
                name = "aespa 에스파 'Supernova' MV",
                videoId = "",
                channelId = "",
                description = "lorem"
            ),
        )
    }

    private fun getCategoryChannelList() = viewModelScope.launch {
        _getCategoryChannelList.value = listOf(
            YoutubeChannelEntity(
                thumbnail ="https://yt3.ggpht.com/1Lm2_GxOcm-dl799vDgA_uHfYskuOOpQ_VhRcth1V7PsNseWEeKhOr8TCPtPrT3Hpo3kYCrNcZs=s88-c-k-c0x00ffffff-no-rj" ,
                name = "Ubisoft",
                description = "When talent meets innovation, gathered to achieve the best gaming experience. Welcome to the official Ubisoft channel.",
                channelId = "UC0KU8F9jJqSLS11LRXvFWmg"
            ),
            YoutubeChannelEntity(
                thumbnail ="https://yt3.ggpht.com/1Lm2_GxOcm-dl799vDgA_uHfYskuOOpQ_VhRcth1V7PsNseWEeKhOr8TCPtPrT3Hpo3kYCrNcZs=s88-c-k-c0x00ffffff-no-rj" ,
                name = "Ubisoft",
                description = "When talent meets innovation, gathered to achieve the best gaming experience. Welcome to the official Ubisoft channel.",
                channelId = "UC0KU8F9jJqSLS11LRXvFWmg"
            ),
            YoutubeChannelEntity(
                thumbnail ="https://yt3.ggpht.com/1Lm2_GxOcm-dl799vDgA_uHfYskuOOpQ_VhRcth1V7PsNseWEeKhOr8TCPtPrT3Hpo3kYCrNcZs=s88-c-k-c0x00ffffff-no-rj" ,
                name = "Ubisoft",
                description = "When talent meets innovation, gathered to achieve the best gaming experience. Welcome to the official Ubisoft channel.",
                channelId = "UC0KU8F9jJqSLS11LRXvFWmg"
            ),
            YoutubeChannelEntity(
                thumbnail ="https://yt3.ggpht.com/1Lm2_GxOcm-dl799vDgA_uHfYskuOOpQ_VhRcth1V7PsNseWEeKhOr8TCPtPrT3Hpo3kYCrNcZs=s88-c-k-c0x00ffffff-no-rj" ,
                name = "Ubisoft",
                description = "When talent meets innovation, gathered to achieve the best gaming experience. Welcome to the official Ubisoft channel.",
                channelId = "UC0KU8F9jJqSLS11LRXvFWmg"
            ),
            YoutubeChannelEntity(
                thumbnail ="https://yt3.ggpht.com/1Lm2_GxOcm-dl799vDgA_uHfYskuOOpQ_VhRcth1V7PsNseWEeKhOr8TCPtPrT3Hpo3kYCrNcZs=s88-c-k-c0x00ffffff-no-rj" ,
                name = "Ubisoft",
                description = "When talent meets innovation, gathered to achieve the best gaming experience. Welcome to the official Ubisoft channel.",
                channelId = "UC0KU8F9jJqSLS11LRXvFWmg"
            ),
        )
    }

    fun getLists(){
        getCategoryList()
        getPopularList()
        getCategoryChannelList()
    }
}

class HomeViewModelFactory: ViewModelProvider.Factory{
    private val repository = SearchRepositoryImpl(RetrofitClient.youtubeService)

    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras
    ): T {
        return HomeViewModel(repository) as T
    }
}