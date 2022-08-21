package com.anastasia.develop.learneasy.data.room.dao

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.*
import com.anastasia.develop.learneasy.data.Module
import com.anastasia.develop.learneasy.data.ModuleWithWords

@Dao
interface ModulesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(module: Module): Long

    @Delete
    suspend fun delete(module: Module)

    @Transaction
    @Query("SELECT * FROM modules_table")
    fun getAll(): List<ModuleWithWords>


    @Transaction
    @Query("SELECT * FROM modules_table WHERE id = :moduleId")
    fun getById(moduleId: Long): ModuleWithWords
}