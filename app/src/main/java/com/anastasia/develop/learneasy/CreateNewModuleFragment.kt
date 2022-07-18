package com.anastasia.develop.learneasy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.anastasia.develop.learneasy.databinding.FragmentCreateNewModuleBinding


class CreateNewModuleFragment : Fragment() {


    private val adapter = CreateNewModuleAdapter()
    lateinit var binding: FragmentCreateNewModuleBinding
    var words = mutableListOf(Word("", ""))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateNewModuleBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter.onAddNewWordClick = {
            words.add(Word("", ""))
            adapter.setData(words)
            adapter.notifyDataSetChanged()
        }

        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.rvCreateNewModel.layoutManager = linearLayoutManager
        binding.rvCreateNewModel.adapter = adapter

        adapter.setData(words)

        binding.btnSave.setOnClickListener {
            Repository.repository.saveModule(Module(
                binding.etModuleName.text.toString(),
                words
            ))
            findNavController().popBackStack()
        }

    }
}