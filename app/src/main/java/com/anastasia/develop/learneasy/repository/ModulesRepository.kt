package com.anastasia.develop.learneasy.repository

import androidx.lifecycle.LiveData
import com.anastasia.develop.learneasy.data.Module
import com.anastasia.develop.learneasy.data.ModuleWithWords
import com.anastasia.develop.learneasy.data.Word

interface ModulesRepository {
    //
//    val modules: LiveData<MutableList<Module>>
    suspend fun getAllModules(): List<ModuleWithWords>
    suspend fun getModuleById(moduleId:Long): ModuleWithWords
    suspend fun insertModule(module: Module): Long
    suspend fun insertWords(words: List<Word>)
    suspend fun deleteModule(module: Module, onSuccess: () -> Unit)
    suspend fun updateWord(word: Word)
}