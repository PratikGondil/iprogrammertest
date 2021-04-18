package com.iprogrammer.roomdatabase.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.androidtraining.personalrutineapp.converter.DateTypeConverter
import com.androidtraining.personalrutineapp.converter.ListConverter
import com.iprogrammer.roomdatabase.dao.WheatherDao
import com.iprogrammer.roomdatabase.entity.Wheather

@Database(entities = [Wheather::class], version = 1)
@TypeConverters(DateTypeConverter::class, ListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun wheatherDao(): WheatherDao

    companion object {
        var INSTANCE: AppDatabase? = null

        fun getAppDataBase(context: Context): AppDatabase? {
            if (INSTANCE == null){
                synchronized(AppDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "wheatherDB").allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase(){
            INSTANCE = null
        }
    }
}