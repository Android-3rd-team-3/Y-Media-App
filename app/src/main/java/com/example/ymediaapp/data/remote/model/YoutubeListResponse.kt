package com.example.ymediaapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class YoutubeListResponse<T>(
    @SerializedName("kind")
    val kind: String?,
    @SerializedName("etag")
    val etag: String?,
    @SerializedName("nextPageToken")
    val nextPageToken: String?,
    @SerializedName("prevPageToken")
    val prevPageToken: String?,
    @SerializedName("pageInfo")
    val pageInfo: PageInfoResponse?,
    @SerializedName("items")
    val items: List<T>?,
)

data class PageInfoResponse(
    @SerializedName("totalResults")
    val totalResults: String?,
    @SerializedName("resultsPerPage")
    val resultsPerPage: String?,
)

