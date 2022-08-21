package com.anastasia.develop.learneasy.ui.cards

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anastasia.develop.learneasy.data.Word
import com.anastasia.develop.learneasy.databinding.ItemRvCardBinding

class CardsAdapter : RecyclerView.Adapter<CardsAdapter.CardsViewHolder>() {

    private val words = mutableListOf<Word>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardsViewHolder {
        return CardsViewHolder(ItemRvCardBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: CardsViewHolder, position: Int) {
        holder.bind(words[position])
    }

    override fun getItemCount() = words.size

    fun setData(list: List<Word>) {
        words.clear()
        words.addAll(list)
    }

    class CardsViewHolder(private val binding: ItemRvCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(word: Word) {
            binding.cardWordLayoutFront.txtFrontCard.text = word.value
            binding.cardWordLayoutBack.txtBackCard.text = word.valueTranslation
        }
    }
}