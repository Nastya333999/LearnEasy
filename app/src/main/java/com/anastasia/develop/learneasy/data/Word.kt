package com.anastasia.develop.learneasy.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "words_table")
data class Word(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    @ColumnInfo
    var value: String,

    @ColumnInfo
    var valueTranslation: String,

    @ColumnInfo
    var moduleId: Long = 0
) : Parcelable
