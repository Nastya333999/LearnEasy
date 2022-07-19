package com.anastasia.develop.learneasy.ui.module

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anastasia.develop.learneasy.data.Module

class ModuleViewModel : ViewModel() {
    fun setModule(value: Module) {
        module.postValue(value)
    }

    val module = MutableLiveData<Module>()
}