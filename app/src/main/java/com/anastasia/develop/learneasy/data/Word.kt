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
    var moduleId: Long = 0,

    @ColumnInfo
    val status: Int
) : Parcelable{
    companion object{
        const val STATUS_INIT = 0
        const val STATUS_LEFT = 1
        const val STATUS_RIGHT = 2
        const val STATUS_DONE = 3
    }
}
