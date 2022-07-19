package com.anastasia.develop.learneasy.data

import android.util.Log

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