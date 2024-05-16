package com.example.ymediaapp.presentation.repository

import com.example.ymediaapp.presentation.entity.CategoryEntity
import com.example.ymediaapp.presentation.entity.CategoryResultEntity
import com.example.ymediaapp.presentation.entity.YoutubeChannelResultEntity
import com.example.ymediaapp.presentation.entity.YoutubeVideoResultEntity

interface SearchRepository {
    // 인기 동영상 받아오기
    suspend fun getPopularList(): YoutubeVideoResultEntity
    // 카테고리 비디오 목록 받아오기 - categoryEntity의 id 값 이용
    suspend fun getVideoByCategoryList(categoryEntity: CategoryEntity): YoutubeVideoResultEntity
    // 카테고리 채널 목록 받아오기 - categoryEntity의 id 값 이용
    suspend fun getChannelByCategoryList(categoryEntity: CategoryEntity): YoutubeChannelResultEntity
    // 카테고리 목록 받아오기(assignment true만?)
    suspend fun getCategoryList(): CategoryResultEntity
}