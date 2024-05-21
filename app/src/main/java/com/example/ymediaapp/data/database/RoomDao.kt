package com.example.ymediaapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RoomDao {

    @Query("SELECT * FROM RoomEntity")
    fun getAllData(): LiveData<List<RoomEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(roomData: RoomEntity)

    @Delete
    suspend fun deleteData(roomData: RoomEntity)

}