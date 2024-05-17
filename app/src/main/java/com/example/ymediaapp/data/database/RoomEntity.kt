package com.example.ymediaapp.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ymediaapp.presentation.entity.YoutubeVideoEntity

@Entity
data class RoomEntity(

    //autoGenerate -> 아이디가 하나씩 추가될 수 있도록 자동 생성, PrimaryKey -> 식별 가능한 키로 선언
    //PrimaryKey -> 추후 변경될 수 있습니다
    @PrimaryKey(autoGenerate = true) val id : Int? = null,
    @ColumnInfo(name = "YoutubeVideoEntity") val video : List<YoutubeVideoEntity>? = null,
    @ColumnInfo(name = "YoutubeVideoID") val videlId: Int? = null
)
