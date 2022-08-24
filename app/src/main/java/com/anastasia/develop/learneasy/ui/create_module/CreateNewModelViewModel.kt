package com.anastasia.develop.learneasy.ui.create_module

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anastasia.develop.learneasy.data.AppDataBase
import com.anastasia.develop.learneasy.repository.Repository
import com.anastasia.develop.learneasy.data.Module
import com.anastasia.develop.learneasy.data.ModuleWithWords
import com.anastasia.develop.learneasy.data.Word
import com.anastasia.develop.learneasy.repository.ModuleRealisation
import com.anastasia.develop.learneasy.repository.ModulesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateNewModelViewModel(application: Application) : AndroidViewModel(application) {

    private val dataBase = AppDataBase.getInstance(application)
    private val repository: ModulesRepository = ModuleRealisation(dataBase)

    var words = MutableLiveData(
        mutableListOf(
            Word(
                value = "",
                valueTranslation = "",
                status = Word.STATUS_INIT
            )
        )
    )

    fun addNewWord() {
        val list = words.value
        list?.add(
            Word(
                value = "",
                valueTranslation = "",
                status = Word.STATUS_INIT
            )
        )
        words.postValue(list)
    }

    fun saveModule(moduleName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val newModuleId = repository.insertModule(Module(name = moduleName))

            val wordsToSave = words.value ?: listOf()

            wordsToSave.forEach { it.moduleId = newModuleId }

            repository.insertWords(wordsToSave)
        }
    }
}