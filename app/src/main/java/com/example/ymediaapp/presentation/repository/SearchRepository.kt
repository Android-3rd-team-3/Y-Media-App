package com.example.ymediaapp.presentation.repository

import com.example.ymediaapp.presentation.entity.CategoryResultEntity
import com.example.ymediaapp.presentation.entity.SearchVideoResultEntity
import com.example.ymediaapp.presentation.entity.YoutubeChannelResultEntity
import com.example.ymediaapp.presentation.entity.YoutubeVideoResultEntity

interface SearchRepository {
    // 인기 동영상 받아오기
    suspend fun getPopularList(): YoutubeVideoResultEntity
    // 카테고리 비디오 목록 받아오기 - categoryEntity의 id 값 이용
    suspend fun getVideoByCategoryList(categoryId: String): YoutubeVideoResultEntity
    // 카테고리 채널 목록 받아오기 - channel id를 "," 으로 구분지어서 스트링으로 만들어 한번에 넘기기
    suspend fun getChannelByCategoryList(channelIds: String): YoutubeChannelResultEntity
    // 카테고리 목록 받아오기(assignment true만?)
    suspend fun getCategoryList(): CategoryResultEntity
    // 검색 동영상 받아오기
//    suspend fun getSearchList(q: String): SearchVideoResultEntity
}