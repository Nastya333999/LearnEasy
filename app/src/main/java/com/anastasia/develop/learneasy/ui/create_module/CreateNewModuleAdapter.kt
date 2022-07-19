package com.anastasia.develop.learneasy.ui.create_module

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.anastasia.develop.learneasy.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.anastasia.develop.learneasy.data.Word

class CreateNewModuleAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val words = mutableListOf<Word>()
    var onAddNewWordClick: (() -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ITEM_TYPE_WORD) {
            val v =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_rv_add_word, parent, false)
            NewModuleHolder(v)
        } else {
            val v =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_rv_add_word_footter, parent, false)
            AddWordViewHolder(v)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is NewModuleHolder) {
            val word: Word = words[position]
            holder.bind(word)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == words.size) {
            ITEM_TYPE_ADD_BTN
        } else {
            ITEM_TYPE_WORD
        }
    }

    fun setData(list: List<Word>) {
        words.clear()
        words.addAll(list)
    }

    override fun getItemCount(): Int = words.size + 1

    inner class NewModuleHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val etAddNewWord = itemView.findViewById<EditText>(R.id.et_add_new_word)
        private val etAddTranslation = itemView.findViewById<EditText>(R.id.et_add_translation)
        private var word: Word? = null

        init {
            etAddNewWord.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun afterTextChanged(text: Editable?) {
                    word?.value = text.toString()
                }
            })

            etAddTranslation.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun afterTextChanged(text: Editable?) {
                    word?.valueTranslation = text.toString()
                }
            })
        }

        fun bind(word: Word) {
            this.word = word
            etAddNewWord.setText(word.value)
            etAddTranslation.setText(word.valueTranslation)
        }
    }

    inner class AddWordViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        init {
            view.findViewById<FloatingActionButton>(R.id.btn_add_word).setOnClickListener {
                onAddNewWordClick?.invoke()
            }
        }
    }

    companion object {
        const val ITEM_TYPE_WORD = 1
        const val ITEM_TYPE_ADD_BTN = 2
    }
}