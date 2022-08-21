package com.anastasia.develop.learneasy.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.anastasia.develop.learneasy.data.room.dao.ModulesDao
import com.anastasia.develop.learneasy.data.room.dao.WordsDao

@Database(entities = [Module::class, Word::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun getModuleDao(): ModulesDao
    abstract fun getWordDao(): WordsDao

    companion object {
        private var database: AppDataBase? = null

        @Synchronized
        fun getInstance(context: Context): AppDataBase {
            return if (database == null) {
                database = Room.databaseBuilder(context, AppDataBase::class.java, "db").build()
                database as AppDataBase
            } else {
                database as AppDataBase
            }
        }
    }
}