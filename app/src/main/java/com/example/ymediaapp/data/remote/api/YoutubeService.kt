package com.example.ymediaapp.data.remote.api

import com.example.ymediaapp.data.remote.model.ChannelResponse
import com.example.ymediaapp.data.remote.model.VideoCategoryResponse
import com.example.ymediaapp.data.remote.model.VideoResponse
import com.example.ymediaapp.data.remote.model.YoutubeListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeService {

    @GET("youtube/v3/videoCategories")
    suspend fun getVideoCategories(
        @Query("regionCode") regionCode: String = "KR"
    ): YoutubeListResponse<VideoCategoryResponse>

    //todo maxResults, pageToken, regionCode param 결정하기
    @GET("youtube/v3/videos")
    suspend fun getPopularVideos(
        @Query("part") part: String = "snippet",
        @Query("chart") chart: String = "mostPopular",
        @Query("fields") fields: String = "kind, etag, nextPageToken, prevPageToken, pageInfo, items(kind, etag, id, snippet(publishedAt, channelId, title, description, thumbnails(default), categoryId))"
    ): YoutubeListResponse<VideoResponse>

    @GET("youtube/v3/videos")
    suspend fun getVideosById(
        @Query("id") id: String = "phuiiNCxRMg",
        @Query("part") part: String = "snippet",
        @Query("fields") fields: String = "kind, etag, nextPageToken, prevPageToken, pageInfo, items(kind, etag, id, snippet(publishedAt, channelId, title, description, thumbnails(default), categoryId))"
    ): YoutubeListResponse<VideoResponse>

    //todo maxResults, pageToken
    @GET("youtube/v3/channels")
    suspend fun getChannelsByChannelId(
        @Query("id") id: String = "UC69Z5Vd-Z6bdCC5LKM_lv_g, UCoUDrzyCl1IwU602xdTsM-g",
        @Query("part") part: String = "id, snippet",
        @Query("fields") fields: String = "kind, etag, nextPageToken, prevPageToken, pageInfo, items(kind, etag, id, snippet(title, description, thumbnails(default)))",
    ): YoutubeListResponse<ChannelResponse>
}