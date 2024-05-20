package com.example.ymediaapp.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ymediaapp.presentation.entity.YoutubeVideoEntity

@Entity
data class RoomEntity(

    @PrimaryKey val videoId: String,
    @ColumnInfo(name = "thumbnail") val thumbnail : String? = null,
    @ColumnInfo(name = "name") val name : String? = null,
    @ColumnInfo(name = "description") val description : String? = null,
    @ColumnInfo(name = "channelId") val channelId : String? = null,
    @ColumnInfo(name = "isLike") val isLike : Boolean? = null

)
