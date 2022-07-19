package com.anastasia.develop.learneasy.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Word(
    var value: String,
    var valueTranslation: String
) : Parcelable
