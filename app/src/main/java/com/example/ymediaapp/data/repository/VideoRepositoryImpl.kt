package com.example.ymediaapp.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.ymediaapp.data.database.RoomDao
import com.example.ymediaapp.data.database.RoomEntity
import com.example.ymediaapp.data.database.YoutubeRoomDatabase
import com.example.ymediaapp.domain.entity.YoutubeVideoEntity
import com.example.ymediaapp.domain.repository.VideoRepository

class VideoRepositoryImpl(val context: Context) : VideoRepository {

    private val roomDB: YoutubeRoomDatabase = YoutubeRoomDatabase.getInstance(context)!!
    private val roomDao: RoomDao = roomDB.getRoomDao()

    override fun getVideoData(): LiveData<List<YoutubeVideoEntity>> {

        val entityList = roomDao.getAllData().map {
                room ->
            room.map {
                YoutubeVideoEntity(
                    it.thumbnail,
                    it.name,
                    it.description,
                    it.videoId,
                    it.channelId
                )
            }
        }

        return entityList
    }

    override suspend fun insertVideoData(video: YoutubeVideoEntity) {
        val roomData = RoomEntity(
            video.videoId,
            video.thumbnail,
            video.name,
            video.description,
            video.channelId
        )
        roomDao.insertData(roomData)
    }

    override suspend fun deleteVideoData(video: YoutubeVideoEntity) {
        val roomData = RoomEntity(
            video.videoId,
            video.thumbnail,
            video.name,
            video.description,
            video.channelId
        )
        roomDao.deleteData(roomData)
    }

    override suspend fun getDataById(videoId: String): YoutubeVideoEntity? {
        val roomEntity = roomDao.getDataById(videoId)
        return roomEntity?.let {
            YoutubeVideoEntity(
                it.thumbnail,
                it.name,
                it.description,
                it.videoId,
                it.channelId
            )
        }
    }
}
