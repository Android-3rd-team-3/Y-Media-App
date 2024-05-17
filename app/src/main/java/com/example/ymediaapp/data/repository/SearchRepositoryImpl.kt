package com.example.ymediaapp.data.repository

import com.example.ymediaapp.data.remote.api.YoutubeService
import com.example.ymediaapp.data.remote.model.toEntity
import com.example.ymediaapp.presentation.entity.CategoryResultEntity
import com.example.ymediaapp.presentation.entity.SearchVideoResultEntity
import com.example.ymediaapp.presentation.entity.YoutubeChannelResultEntity
import com.example.ymediaapp.presentation.entity.YoutubeVideoResultEntity
import com.example.ymediaapp.presentation.repository.SearchRepository

class SearchRepositoryImpl(
    private val youtubeService: YoutubeService
): SearchRepository {
    override suspend fun getPopularList(): YoutubeVideoResultEntity {
        return youtubeService.getPopularVideos().toEntity()
    }

    override suspend fun getVideoByCategoryList(categoryId: String): YoutubeVideoResultEntity {
        return youtubeService.getPopularVideosByCategory(categoryId).toEntity()
    }

    override suspend fun getChannelByCategoryList(channelIds: String): YoutubeChannelResultEntity {
        return youtubeService.getChannelsByChannelId(channelIds).toEntity()
    }

    override suspend fun getCategoryList(): CategoryResultEntity {
        return youtubeService.getVideoCategories().toEntity()
    }


}