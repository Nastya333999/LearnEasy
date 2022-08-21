package com.anastasia.develop.learneasy.ui.modules

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anastasia.develop.learneasy.data.AppDataBase
import com.anastasia.develop.learneasy.data.Module
import com.anastasia.develop.learneasy.data.ModuleWithWords
import com.anastasia.develop.learneasy.repository.ModuleRealisation
import com.anastasia.develop.learneasy.repository.ModulesRepository
import com.anastasia.develop.learneasy.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ModulesViewModel(application: Application) : AndroidViewModel(application) {

    private val dataBase = AppDataBase.getInstance(application)
    private val repository: ModulesRepository = ModuleRealisation(dataBase)
    var modules = MutableLiveData<List<ModuleWithWords>>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            modules.postValue(repository.getAllModules())
        }
    }

}