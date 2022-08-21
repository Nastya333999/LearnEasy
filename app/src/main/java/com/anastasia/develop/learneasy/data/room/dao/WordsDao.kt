package com.anastasia.develop.learneasy.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.anastasia.develop.learneasy.data.Module
import com.anastasia.develop.learneasy.data.Word

@Dao
interface WordsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    @Delete
    suspend fun delete(word: Word)

    @Query("SELECT * FROM words_table")
    fun getAll(): List<Word>
}