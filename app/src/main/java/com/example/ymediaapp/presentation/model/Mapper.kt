package com.example.ymediaapp.presentation.model

import com.example.ymediaapp.domain.entity.CategoryEntity
import com.example.ymediaapp.domain.entity.SearchVideoEntity
import com.example.ymediaapp.domain.entity.YoutubeChannelEntity
import com.example.ymediaapp.domain.entity.YoutubeVideoEntity
import java.util.Date

fun YoutubeChannelEntity.toModel() = YoutubeChannelModel(
    thumbnail,
    name,
    description,
    channelId
)


fun YoutubeVideoEntity.toModel() = YoutubeVideoModel(
    thumbnail,
    name,
    description,
    videoId,
    channelId
)

fun CategoryEntity.toModel() = CategoryModel(
    id,
    name,
    assignable
)

fun YoutubeVideoModel.toEntity() = YoutubeVideoEntity(
    thumbnail = thumbnail,
    name = name,
    description = description,
    videoId = videoId,
    channelId = channelId
)

fun SearchVideoEntity.toModel() = SearchVideoModel(
    thumbnail,
    title,
    channel,
    id,
    dateTime
)

fun SearchVideoModel.toEntity() = SearchVideoEntity(
    thumbnail = thumbnail,
    title = title,
    channel = channel,
    id = id,
    dateTime = dateTime
)
