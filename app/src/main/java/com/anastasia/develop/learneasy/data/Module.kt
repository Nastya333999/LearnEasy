package com.anastasia.develop.learneasy.data

import android.os.Parcelable
import com.anastasia.develop.learneasy.data.Word
import kotlinx.parcelize.Parcelize

@Parcelize
data class Module(
    val name: String,
    val words:List<Word>
) : Parcelable
