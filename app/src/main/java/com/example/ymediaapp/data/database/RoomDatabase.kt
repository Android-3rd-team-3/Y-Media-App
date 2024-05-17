package com.example.ymediaapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(RoomEntity::class), version = 1)

abstract class RoomDatabase : RoomDatabase() {

    abstract fun getRoomDao() : RoomDao

    companion object {
        val databaseName = "db_room"
        var appDatabase : RoomDatabase? = null

        fun getInstance(context : Context) : RoomDatabase? {
            if(appDatabase == null){
                appDatabase = Room.databaseBuilder(context,
                    RoomDatabase::class.java,
                    databaseName).
                    //마이그레이션이 실패할 때 db테이블 재생성, 데이터 사라질 수 있음
                fallbackToDestructiveMigration()
                    .build()

            }
            return appDatabase
        }


    }

}
