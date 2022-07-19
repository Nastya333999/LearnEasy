package com.anastasia.develop.learneasy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.anastasia.develop.learneasy.data.Repository
import com.anastasia.develop.learneasy.data.Word
import com.anastasia.develop.learneasy.databinding.FragmentAddNewBinding

class AddNewFragment : Fragment() {

    lateinit var binding: FragmentAddNewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddNewBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAddNewWords.setOnClickListener {
            val word = Word(
                value = binding.etAddNew.text.toString(),
                valueTranslation = binding.etAddTranslate.text.toString()
            )
            Repository.repository.addWord(word)

            Toast.makeText(context, "Word added", Toast.LENGTH_SHORT).show()
            binding.etAddNew.setText("")
            binding.etAddTranslate.setText("")
        }
    }
}