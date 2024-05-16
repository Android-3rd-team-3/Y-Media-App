package com.example.ymediaapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class VideoCategoryResponse(
    @SerializedName("kind")
    val kind: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("etag")
    val etag: String?,
    @SerializedName("snippet")
    val snippet: VideoCategorySnippetResponse?
)

data class VideoCategorySnippetResponse(
    @SerializedName("channelId")
    val channelId: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("assignable")
    val assignable: Boolean?,
)
