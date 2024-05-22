package com.example.ymediaapp.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date
@Parcelize
data class YoutubeVideoModel( // 동영상
    val thumbnail: String,// 썸네일 URL
    val name: String,// 이름
    val description: String, // 설명
    val videoId: String, // 해당 동영상의 ID
    val channelId: String, // 채널id
):Parcelable


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