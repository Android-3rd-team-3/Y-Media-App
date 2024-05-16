package com.example.ymediaapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class ThumbnailsResponse(
    @SerializedName("default")
    val default: ThumbnailInfoResponse?
)

data class ThumbnailInfoResponse(
    @SerializedName("url")
    val url: String?,
    @SerializedName("width")
    val width: Int?,
    @SerializedName("height")
    val height: Int?,
)