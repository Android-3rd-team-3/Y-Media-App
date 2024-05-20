package com.example.ymediaapp.data.remote.model

import com.example.ymediaapp.presentation.entity.CategoryEntity
import com.example.ymediaapp.presentation.entity.CategoryResultEntity
import com.example.ymediaapp.presentation.entity.YoutubeChannelEntity
import com.example.ymediaapp.presentation.entity.YoutubeChannelResultEntity
import com.example.ymediaapp.presentation.entity.YoutubeVideoEntity
import com.example.ymediaapp.presentation.entity.YoutubeVideoResultEntity

fun YoutubeListResponse<VideoResponse>.toEntity() = YoutubeVideoResultEntity(
    kind = kind?:"",
    eTag = etag?:"",
    nextPageToken = nextPageToken?:"",
    prevPageToken = prevPageToken?:"",
    items = items?.map {
        it.toEntity()
    }.orEmpty()
)

fun VideoResponse.toEntity() = YoutubeVideoEntity(
    thumbnail = snippet?.thumbnails?.default?.url?:"",
    channelId = snippet?.channelId?:"",
    name = snippet?.title?:"",
    description = snippet?.description?:"",
    videoId = id?:"",
)

fun YoutubeListResponse<ChannelResponse>.toEntity() = YoutubeChannelResultEntity(
    kind = kind?:"",
    eTag = etag?:"",
    items = items?.map {
        it.toEntity()
    }.orEmpty()
)

fun ChannelResponse.toEntity() = YoutubeChannelEntity(
    thumbnail = snippet?.thumbnails?.default?.url?:"",
    name = snippet?.title?:"",
    description = snippet?.description?:"",
    channelId = id?:""
)

fun YoutubeListResponse<VideoCategoryResponse>.toEntity() = CategoryResultEntity(
    kind = kind?:"",
    eTag = etag?:"",
    items = items?.map {
        it.toEntity()
    }.orEmpty()
)

fun VideoCategoryResponse.toEntity() = CategoryEntity(
    id = id?:"",
    name = snippet?.title?:"",
    assignable = snippet?.assignable?:false
)