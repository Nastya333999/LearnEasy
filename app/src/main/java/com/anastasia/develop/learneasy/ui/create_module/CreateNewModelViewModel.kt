package com.anastasia.develop.learneasy.ui.create_module

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anastasia.develop.learneasy.data.Repository
import com.anastasia.develop.learneasy.data.Module
import com.anastasia.develop.learneasy.data.Word

class CreateNewModelViewModel : ViewModel() {

    var words = MutableLiveData(mutableListOf(Word("", "")))

    fun addNewWord() {
        val list = words.value
        list?.add(Word("", ""))
        words.postValue(list)
    }

    fun saveModule(moduleName: String) {
        Repository.repository.saveModule(
            Module(
                moduleName,
                words.value ?: listOf()
            )
        )
    }
}