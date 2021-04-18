package com.iprogrammer.roomdatabase.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Wheather(
        @PrimaryKey(autoGenerate = true)
        val id:Int?=null,
        val cityname: String? = "",
        val mintemp: String="",
        val maxtemp: String="",
        val date: String=""
        )