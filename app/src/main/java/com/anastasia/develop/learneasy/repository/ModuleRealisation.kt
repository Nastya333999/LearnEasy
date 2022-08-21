package com.anastasia.develop.learneasy.repository

import com.anastasia.develop.learneasy.data.AppDataBase
import com.anastasia.develop.learneasy.data.Module
import com.anastasia.develop.learneasy.data.ModuleWithWords
import com.anastasia.develop.learneasy.data.Word

class ModuleRealisation(private val appDataBase: AppDataBase) : ModulesRepository {

//    override val modules: LiveData<MutableList<Module>>
//        get() =

    override suspend fun getAllModules(): List<ModuleWithWords> {
        return appDataBase.getModuleDao().getAll()
    }

    override suspend fun getModuleById(moduleId: Long): ModuleWithWords {
        return appDataBase.getModuleDao().getById(moduleId)
    }

    override suspend fun insertModule(module: Module): Long {
        return appDataBase.getModuleDao().insert(module)
    }

    override suspend fun insertWords(words: List<Word>) {
        words.forEach { appDataBase.getWordDao().insert(it) }
    }

    override suspend fun deleteModule(module: Module, onSuccess: () -> Unit) {
        appDataBase.getModuleDao().delete(module)
        onSuccess()
    }
}