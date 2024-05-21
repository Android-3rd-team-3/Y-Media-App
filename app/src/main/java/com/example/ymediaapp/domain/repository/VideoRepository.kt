package com.example.ymediaapp.domain.repository

import androidx.lifecycle.LiveData
import com.example.ymediaapp.domain.entity.YoutubeVideoEntity
import com.example.ymediaapp.presentation.model.YoutubeVideoModel

interface VideoRepository {
    fun getVideoData(): LiveData<List<YoutubeVideoEntity>>

   suspend fun insertVideoData(video: YoutubeVideoEntity)

   suspend fun deleteVideoData(video: YoutubeVideoEntity)
}
