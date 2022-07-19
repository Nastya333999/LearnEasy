package com.anastasia.develop.learneasy.ui.modules

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anastasia.develop.learneasy.data.Module
import com.anastasia.develop.learneasy.data.Repository

class ModulesViewModel: ViewModel() {

    var modules = MutableLiveData<List<Module>>()

    init {
        modules.postValue(Repository.repository.getModules())
    }

}