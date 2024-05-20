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
        _getSearchList.value = listOf(
            SearchVideoEntity(
                thumbnail = "https://i.ytimg.com/vi/phuiiNCxRMg/default.jpg",
                title = "aespa 에스파 'Supernova' MV",
                channel = "lorem",
                id = "",
                dateTime = "2023-05-08T20:51:46.000Z"
            ),
            SearchVideoEntity(
                thumbnail = "https://i.ytimg.com/vi/phuiiNCxRMg/default.jpg",
                title = "식비절약 3만원으로 장봐서 5일 집밥 해먹기 5월2주차 | 식비절약브이로그 | 집밥브이로그",
                channel = "프롬투데이 fromTODAY",
                id = "",
                dateTime = "2023-05-08T20:51:46.000Z"
            ),
            SearchVideoEntity(
                thumbnail = "https://i.ytimg.com/vi/phuiiNCxRMg/default.jpg",
                title = "상대를 농락하려면 꼭 알아야 할 심리와 타이밍 (전 프로축구선수 나진성 감독)",
                channel = "축구도사 메기 Megi",
                id = "",
                dateTime = "2023-05-08T20:51:46.000Z"
            ),
            SearchVideoEntity(
                thumbnail = "https://i.ytimg.com/vi/phuiiNCxRMg/default.jpg",
                title = "남들 다 출근했는데 혼자 뭐하세요..? 부대찌개에 낮술이요 (박가부대찌개, 닭갈비, 계란말이)",
                channel = "엄비umbi",
                id = "",
                dateTime = "2023-05-08T20:51:46.000Z"
            ),
            SearchVideoEntity(
                thumbnail = "https://i.ytimg.com/vi/phuiiNCxRMg/default.jpg",
                title = "무릎아픈 3인, 3주간 근육운동 결과는? (KBS 20211103 방송)",
                channel = "KBS 생로병사의 비밀",
                id = "",
                dateTime = "2023-05-08T20:51:46.000Z"
            ),
            SearchVideoEntity(
                thumbnail = "https://i.ytimg.com/vi/phuiiNCxRMg/default.jpg",
                title = "식비절약 3만원으로 장봐서 5일 집밥 해먹기 5월2주차 | 식비절약브이로그 | 집밥브이로그",
                channel = "프롬투데이 fromTODAY",
                id = "",
                dateTime = "2023-05-08T20:51:46.000Z"
            ),
            SearchVideoEntity(
                thumbnail = "https://i.ytimg.com/vi/phuiiNCxRMg/default.jpg",
                title = "상대를 농락하려면 꼭 알아야 할 심리와 타이밍 (전 프로축구선수 나진성 감독)",
                channel = "축구도사 메기 Megi",
                id = "",
                dateTime = "2023-05-08T20:51:46.000Z"
            ),
            SearchVideoEntity(
                thumbnail = "https://i.ytimg.com/vi/phuiiNCxRMg/default.jpg",
                title = "aespa 에스파 'Supernova' MV",
                channel = "lorem",
                id = "",
                dateTime = "2023-05-08T20:51:46.000Z"
            )
        )
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