package com.anastasia.develop.learneasy.ui.cards

import android.app.Application
import android.util.Log
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
    val learnedWordsCount = MutableLiveData(0)
    val skippedWordsCount = MutableLiveData(0)
    val swippedWordsCount = MutableLiveData(0)
    val repository = ModuleRealisation(AppDataBase.getInstance(application))
    val allWordsCount = MutableLiveData(0)
    private var moduleId = 0L

    fun setModule(moduleId: Long) {
        this.moduleId = moduleId
        viewModelScope.launch(Dispatchers.IO) {
            val moduleWithWords = repository.getModuleById(moduleId)
            val allWords = moduleWithWords.words
            val wordsInInitStatus = allWords.filter { it.status == Word.STATUS_INIT }
            words.postValue(wordsInInitStatus)
            val rightStatusWordsCount = allWords.filter { it.status == Word.STATUS_RIGHT }.size
            val leftStatusWordsCount = allWords.filter { it.status == Word.STATUS_LEFT }.size
            skippedWordsCount.postValue(leftStatusWordsCount)
            learnedWordsCount.postValue(rightStatusWordsCount)
            swippedWordsCount.postValue(leftStatusWordsCount + rightStatusWordsCount)
            allWordsCount.postValue(leftStatusWordsCount + rightStatusWordsCount + wordsInInitStatus.size)
        }
    }

    fun swipeRight(position: Int) {
        learnedWordsCount.postValue(learnedWordsCount.value!! + 1)
        swippedWordsCount.postValue(swippedWordsCount.value!! + 1)
        updateWord(position, Word.STATUS_RIGHT)
    }

    fun swipeLeft(position: Int) {
        skippedWordsCount.postValue(skippedWordsCount.value!! + 1)
        swippedWordsCount.postValue(swippedWordsCount.value!! + 1)
        updateWord(position, Word.STATUS_LEFT)
    }

    private fun updateWord(position: Int, newStatus: Int) {
        val word = words.value?.get(position)

        word?.let {
            val updatedWord = it.copy(status = newStatus)

            viewModelScope.launch(Dispatchers.IO) {
                repository.updateWord(updatedWord)
            }
        }

        if (position == words.value!!.size - 1) {
            viewModelScope.launch(Dispatchers.IO) {
                val moduleWithWords = repository.getModuleById(moduleId)


                val moduleSize = moduleWithWords.words.size
                val progressStep = 100 / moduleSize

                Log.d("words", "step = $progressStep")

                val isNeedReset =
                    moduleWithWords.words.none { it.status == Word.STATUS_INIT || it.status == Word.STATUS_LEFT }
                moduleWithWords.words.forEach {
                    if (it.status == Word.STATUS_LEFT || isNeedReset) {
                        repository.updateWord(it.copy(status = Word.STATUS_INIT))
                    } else if (it.status == Word.STATUS_RIGHT) {
                        repository.updateWord(it.copy(status = Word.STATUS_DONE))
                    }
                }
            }
        }
    }

}