package com.example.ymediaapp.presentation.repository

import com.example.ymediaapp.data.database.RoomEntity
import com.example.ymediaapp.presentation.entity.YoutubeVideoEntity

interface VideoRepository {
    fun getVideoData(): List<YoutubeVideoEntity>

    fun insertVideoData(video: YoutubeVideoEntity)

    fun deleteVideoData(video: YoutubeVideoEntity)
}

