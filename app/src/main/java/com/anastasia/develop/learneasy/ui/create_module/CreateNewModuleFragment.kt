package com.anastasia.develop.learneasy.ui.create_module

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.anastasia.develop.learneasy.databinding.FragmentCreateNewModuleBinding


class CreateNewModuleFragment : Fragment() {

    private val adapter = CreateNewModuleAdapter()
    lateinit var binding: FragmentCreateNewModuleBinding
    private val viewModel: CreateNewModelViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateNewModuleBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        initObservers()
        initListeners()
    }

    private fun initUi() {
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.rvCreateNewModel.layoutManager = linearLayoutManager
        binding.rvCreateNewModel.adapter = adapter
    }

    private fun initObservers() {
        viewModel.words.observe(viewLifecycleOwner) {
            adapter.setData(it)
            adapter.notifyDataSetChanged()
        }
    }

    private fun initListeners() {
        binding.btnSave.setOnClickListener {
            viewModel.saveModule(binding.etModuleName.text.toString())
            findNavController().popBackStack()
        }
        adapter.onAddNewWordClick = {
            viewModel.addNewWord()
        }
    }
}