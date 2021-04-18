package com.androidtraining.personalrutineapp.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.iprogrammer.roomdatabase.entity.Wheather
import java.util.*

class ListConverter {

    var gson = Gson()

    @TypeConverter
    fun fromTimestamp(data: String?): List<Wheather>? {

        if (data == null){
            return Collections.emptyList()
        }
        val listType = object : TypeToken<ArrayList<String>>() {}.type
        return gson.fromJson(data, listType)


    }

    @TypeConverter
    fun someObjectListToString(someObjects: List<Wheather>?): String? {
        return gson.toJson(someObjects)
    }



}