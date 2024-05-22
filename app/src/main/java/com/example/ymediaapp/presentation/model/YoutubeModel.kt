package com.example.ymediaapp.presentation.model

import java.util.Date

data class YoutubeVideoModel( // 동영상
    val thumbnail: String,// 썸네일 URL
    val name: String,// 이름
    val description: String, // 설명
    val videoId: String, // 해당 동영상의 ID
    val channelId: String, // 채널id
)

data class YoutubeVideoResultModel(
    val kind: String,
    val eTag: String,
    val nextPageToken: String,
    val prevPageToken: String,
    val items: List<YoutubeVideoModel>
)

data class YoutubeChannelModel( // 채널
    val thumbnail: String,// 썸네일 URL
    val name: String,// 이름
    val description: String, // 설명
    val channelId: String
    //val videoList: List<YoutubeVideoEntity>// 비디오 리스트
)

data class YoutubeChannelResultModel(
    val kind: String,
    val eTag: String,
    val items: List<YoutubeChannelModel>
)

data class CategoryModel(
    val id: String,
    val name: String,
    val assignable: Boolean = false
)

data class CategoryResultModel(
    val kind: String,
    val eTag: String,
    val items: List<CategoryModel>
)

data class SearchVideoModel(
    val thumbnail: String,
    val title: String,
    val channel: String,
    val id: String,
    val dateTime: Date
)

data class SearchVideoResultModel(
    val kind: String,
    val eTag: String,
    val nextPageToken: String,
    val prevPageToken: String,
    val items: List<SearchVideoModel>
)