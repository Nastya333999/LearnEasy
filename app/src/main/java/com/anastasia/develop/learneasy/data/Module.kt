package com.anastasia.develop.learneasy.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.anastasia.develop.learneasy.data.Word
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "modules_table")
data class Module(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    @ColumnInfo
    var name: String = ""
) : Parcelable


