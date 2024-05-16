package com.example.ymediaapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class ChannelResponse(
    @SerializedName("kind")
    val kind: String?,
    @SerializedName("etag")
    val etag: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("snippet")
    val snippet: ChannelSnippetResponse?
)

data class ChannelSnippetResponse(
    @SerializedName("title")
    val title: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("thumbnails")
    val thumbnails: ThumbnailsResponse?
)