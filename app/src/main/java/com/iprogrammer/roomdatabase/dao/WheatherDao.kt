package com.iprogrammer.roomdatabase.dao

import androidx.room.*
import com.iprogrammer.roomdatabase.entity.Wheather

@Dao
interface WheatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWheather(wheather: Wheather)

    @Update
    fun updateWheather(wheather: Wheather)

    @Delete
    fun deleteWheather(wheather: Wheather)

    @Query("SELECT * FROM Wheather WHERE cityname == :cityname")
    fun getWheatherByName(cityname: String): List<Wheather>

    @Query("SELECT * FROM Wheather")
    fun getWheather(): List<Wheather>
}