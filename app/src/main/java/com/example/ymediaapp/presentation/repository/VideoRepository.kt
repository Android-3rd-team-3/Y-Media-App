package com.example.ymediaapp.presentation.repository

import androidx.lifecycle.LiveData
import com.example.ymediaapp.data.database.RoomEntity
import com.example.ymediaapp.presentation.entity.YoutubeVideoEntity

interface VideoRepository {
    fun getVideoData(): LiveData<List<YoutubeVideoEntity>>

    fun insertVideoData(video: YoutubeVideoEntity)

    fun deleteVideoData(video: YoutubeVideoEntity)
}

