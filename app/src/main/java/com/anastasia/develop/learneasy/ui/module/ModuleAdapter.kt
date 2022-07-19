package com.anastasia.develop.learneasy.ui.module

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anastasia.develop.learneasy.R
import com.anastasia.develop.learneasy.data.Word

class ModuleAdapter : RecyclerView.Adapter<ModuleAdapter.ModuleViewHolder>() {

    private val words = mutableListOf<Word>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModuleViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_words, parent, false)
        return ModuleViewHolder(v)
    }

    override fun onBindViewHolder(holder: ModuleViewHolder, position: Int) {
        val w: Word = words[position]
        holder.bind(w)
    }

    fun setData(list: List<Word>) {
        words.clear()
        words.addAll(list)
    }

    override fun getItemCount(): Int = words.size

    inner class ModuleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var word: Word? = null
        private var txtWord = itemView.findViewById<TextView>(R.id.txt_word)
        private var txtWordTranslation = itemView.findViewById<TextView>(R.id.txt_word_translation)

        fun bind(word: Word) {
            this.word = word
            txtWord.text = word.value.toString()
            txtWordTranslation.text = word.valueTranslation
        }


    }
}