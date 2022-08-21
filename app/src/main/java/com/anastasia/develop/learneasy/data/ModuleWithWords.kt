package com.anastasia.develop.learneasy.data

import androidx.room.Embedded
import androidx.room.Relation

data class ModuleWithWords(
    @Embedded
    val module: Module,

    @Relation(
        parentColumn = "id",
        entityColumn = "moduleId"
    )

    val words: List<Word>
)
