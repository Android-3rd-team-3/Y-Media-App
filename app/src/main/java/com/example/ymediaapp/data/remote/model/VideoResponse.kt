package com.example.ymediaapp.data.remote.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class VideoResponse(
    @SerializedName("kind")
    val kind: String?,
    @SerializedName("etag")
    val etag: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("snippet")
    val snippet: VideoSnippetResponse?,
    @SerializedName("channelTitle")
    val channelTitle: String?,
    @SerializedName("categoryId")
    val categoryId: String?
)

data class VideoSnippetResponse(
    @SerializedName("publishedAt")
    val publishedAt: Date?,
    @SerializedName("channelId")
    val channelId: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("thumbnails")
    val thumbnails: ThumbnailsResponse?,
    @SerializedName("categoryId")
    val categoryId: String?
)

