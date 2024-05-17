package com.example.ymediaapp.data.remote.api

import com.example.ymediaapp.data.remote.model.ChannelResponse
import com.example.ymediaapp.data.remote.model.SearchResponse
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

    @GET("youtube/v3/videos")
    suspend fun getPopularVideos(
        @Query("part") part: String = "snippet",
        @Query("chart") chart: String = "mostPopular",
        @Query("fields") fields: String = "kind, etag, nextPageToken, prevPageToken, pageInfo, items(kind, etag, id, snippet(publishedAt, channelId, title, description, thumbnails(default), categoryId))",
        @Query("maxResults") maxResults: Int = 25,
        @Query("regionCode") regionCode: String = "KR"
    ): YoutubeListResponse<VideoResponse>

    @GET("youtube/v3/videos")
    suspend fun getPopularVideosByCategory(
        @Query("videoCategoryId") videoCategoryId: String,
        @Query("part") part: String = "snippet",
        @Query("chart") chart: String = "mostPopular",
        @Query("fields") fields: String = "kind, etag, nextPageToken, prevPageToken, pageInfo, items(kind, etag, id, snippet(publishedAt, channelId, title, description, thumbnails(default), categoryId))",
        @Query("maxResults") maxResults: Int = 25,
        @Query("regionCode") regionCode: String = "KR"
    ): YoutubeListResponse<VideoResponse>

    @GET("youtube/v3/videos")
    suspend fun getVideosById(
        @Query("id") id: String,
        @Query("part") part: String = "snippet",
        @Query("fields") fields: String = "kind, etag, nextPageToken, prevPageToken, pageInfo, items(kind, etag, id, snippet(publishedAt, channelId, title, description, thumbnails(default), categoryId))"
    ): YoutubeListResponse<VideoResponse>

    @GET("youtube/v3/channels")
    suspend fun getChannelsByChannelId(
        @Query("id") id: String,
        @Query("part") part: String = "id, snippet",
        @Query("fields") fields: String = "kind, etag, nextPageToken, prevPageToken, pageInfo, items(kind, etag, id, snippet(title, description, thumbnails(default)))",
    ): YoutubeListResponse<ChannelResponse>

    @GET("youtube/v3/search")
    suspend fun searchVideos(
        @Query("q") query: String,
        @Query("type") type: String = "video",
        @Query("order") order: String = "relevance",
        @Query("part") part: String = "id, snippet",
        @Query("fields") fields: String = "kind, etag, items(id(videoId), snippet(title, channelTitle, publishedAt, thumbnails(default) ))",
    ): YoutubeListResponse<SearchResponse>
}