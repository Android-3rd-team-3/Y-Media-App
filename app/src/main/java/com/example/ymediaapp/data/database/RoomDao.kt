package com.example.ymediaapp.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RoomDao {

    @Query("SELECT * FROM RoomEntity")
    fun getAllData(): List<RoomEntity>

    @Insert
    fun insertData(): List<RoomEntity>

    @Delete
    fun deleteData(): List<RoomEntity>

}