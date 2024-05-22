package com.example.ymediaapp.presentation.model

import com.example.ymediaapp.domain.entity.CategoryEntity
import com.example.ymediaapp.domain.entity.YoutubeChannelEntity
import com.example.ymediaapp.domain.entity.YoutubeVideoEntity

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
    channelId,
)

fun CategoryEntity.toModel() = CategoryModel(
    id,
    name,
    assignable
)