package com.anastasia.develop.learneasy.ui.cards

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.anastasia.develop.learneasy.data.AppDataBase
import com.anastasia.develop.learneasy.data.ModuleWithWords
import com.anastasia.develop.learneasy.data.Word
import com.anastasia.develop.learneasy.repository.ModuleRealisation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CardsViewModel(application: Application) : AndroidViewModel(application) {
    val words = MutableLiveData<List<Word>>()
    val repository = ModuleRealisation(AppDataBase.getInstance(application))

    fun setModule(moduleId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val moduleWithWords = repository.getModuleById(moduleId)
            words.postValue(moduleWithWords.words)
        }
    }
}