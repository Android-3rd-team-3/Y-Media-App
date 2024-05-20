package com.example.ymediaapp.data.repository

import android.content.Context
import com.example.ymediaapp.data.database.RoomDao
import com.example.ymediaapp.data.database.RoomEntity
import com.example.ymediaapp.data.database.YoutubeRoomDatabase
import com.example.ymediaapp.presentation.entity.YoutubeVideoEntity
import com.example.ymediaapp.presentation.repository.VideoRepository

class VideoRepositoryImpl( val context: Context): VideoRepository {

    lateinit var roomDB: YoutubeRoomDatabase
    lateinit var roomDao: RoomDao
    override fun getVideoData(): List<YoutubeVideoEntity> {

        roomDB = YoutubeRoomDatabase.getInstance(context)!!
        roomDao = roomDB.getRoomDao()

        val allRoomData = roomDao.getAllData()
        val entityList = allRoomData.map {
            YoutubeVideoEntity(
                it.thumbnail ?: "값 없음",
                it.name ?: "값 없음",
                it.description ?: "값 없음",
                it.videoId,
                it.channelId ?: "값 없음",
                it.isLike ?: true
            )
        }

        return  entityList
    }

    override fun insertVideoData(video: YoutubeVideoEntity) {
        val roomData = RoomEntity(video.videoId, video.thumbnail, video.name, video.description, video.channelId, video.isLike)
        roomDao.insertData(roomData)
    }

    override fun deleteVideoData(video: YoutubeVideoEntity) {
        val roomData = RoomEntity(video.videoId, video.thumbnail, video.name, video.description, video.channelId, video.isLike)
        roomDao.deleteData(roomData)
    }


}