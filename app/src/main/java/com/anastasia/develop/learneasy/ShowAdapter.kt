package com.anastasia.develop.learneasy

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anastasia.develop.learneasy.ShowAdapter.*
import com.anastasia.develop.learneasy.data.Word

class ShowAdapter : RecyclerView.Adapter<ShowViewHolder>() {

    var onDeleteClickListener: ((Word) -> Unit)? = null

    private var words = mutableListOf<Word>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_show, parent, false)
        return ShowViewHolder(v)
    }

    fun setData(list: List<Word>) {
        words.clear()
        words.addAll(list)
    }

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
        val w: Word = words[position]
        holder.bind(w, position)
    }

    override fun getItemCount(): Int = words.size

    inner class ShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val txtWord = itemView.findViewById<TextView>(R.id.txt_text_word)
        private var txtNumber = itemView.findViewById<TextView>(R.id.txt_number)
        private val txtTranslation = itemView.findViewById<TextView>(R.id.txt_translation)
        private val imgBtnDeleteWord = itemView.findViewById<ImageButton>(R.id.imgbtn_delete_word)
        private var focusedWord: Word? = null

        init {
            imgBtnDeleteWord.setOnClickListener {
                focusedWord?.let { onDeleteClickListener?.invoke(it) }
            }
        }

        fun bind(word: Word, position: Int) {
            focusedWord = word
            Log.d("adapter", "bind data = $word")
            txtWord.text = word.value
            txtNumber.text = (position + 1).toString()
            txtTranslation.text = word.valueTranslation
        }
    }
}