package com.anastasia.develop.learneasy.ui.module

import android.app.Application
import androidx.lifecycle.*
import com.anastasia.develop.learneasy.data.AppDataBase
import com.anastasia.develop.learneasy.data.Module
import com.anastasia.develop.learneasy.data.ModuleWithWords
import com.anastasia.develop.learneasy.data.REPOSITORY
import com.anastasia.develop.learneasy.repository.ModuleRealisation
import com.anastasia.develop.learneasy.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ModuleViewModel (application: Application) : AndroidViewModel(application) {

    val module = MutableLiveData<ModuleWithWords>()
    val repository = ModuleRealisation(AppDataBase.getInstance(application))

    fun setModule(moduleId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val value = repository.getModuleById(moduleId)
            value.words
            module.postValue(value)
        }
    }
}