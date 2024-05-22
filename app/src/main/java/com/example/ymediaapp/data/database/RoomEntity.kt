package com.example.ymediaapp.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ymediaapp.domain.entity.YoutubeVideoEntity

@Entity
data class RoomEntity(

    @PrimaryKey val videoId: String,
    @ColumnInfo(name = "thumbnail") val thumbnail : String,
    @ColumnInfo(name = "name") val name : String,
    @ColumnInfo(name = "description") val description : String,
    @ColumnInfo(name = "channelId") val channelId : String,
//    @ColumnInfo(name = "isLike") val isLike : Boolean

)
