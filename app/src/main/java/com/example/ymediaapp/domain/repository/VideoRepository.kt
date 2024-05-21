package com.example.ymediaapp.domain.repository

import androidx.lifecycle.LiveData
import com.example.ymediaapp.data.database.RoomEntity
import com.example.ymediaapp.domain.entity.YoutubeVideoEntity

interface VideoRepository {
    fun getVideoData(): LiveData<List<YoutubeVideoEntity>>

   suspend fun insertVideoData(video: YoutubeVideoEntity)

   suspend fun deleteVideoData(video: YoutubeVideoEntity)
}
