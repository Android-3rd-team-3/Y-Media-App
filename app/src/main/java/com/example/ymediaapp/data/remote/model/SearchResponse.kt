package com.example.ymediaapp.data.remote.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class SearchResponse(
    @SerializedName("id")
    val id: SearchIdResponse?,
    @SerializedName("snippet")
    val snippet: SearchSnippetResponse?
)

data class SearchIdResponse(
    @SerializedName("videoId")
    val videoId: String?
)

data class SearchSnippetResponse(
    @SerializedName("publishedAt")
    val publishedAt: Date?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("thumbnails")
    val thumbnails: ThumbnailsResponse?,
    @SerializedName("channelTitle")
    val channelTitle: String?
)