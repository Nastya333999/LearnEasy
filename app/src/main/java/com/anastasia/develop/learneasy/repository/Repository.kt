package com.anastasia.develop.learneasy.repository

import android.util.Log
import com.anastasia.develop.learneasy.data.Module
import com.anastasia.develop.learneasy.data.Word

class Repository {
    private val words = mutableListOf<Word>()
    private val modules = mutableListOf<Module>()

    fun addWord(word: Word) {
        words.add(word)
    }

    fun getAllWords(): List<Word> {
        return words
    }

    fun deleteWord(word: Word) {
        words.remove(word)
    }

    fun saveModule(module: Module) {
        modules.add(module)
        Log.d("modules",  modules.toString())
    }

    fun getModules(): List<Module> {
        return modules
    }

    companion object {
        val repository = Repository()
    }
}