package com.anastasia.develop.learneasy.ui.module

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.anastasia.develop.learneasy.databinding.FragmentModuleBinding
import com.anastasia.develop.learneasy.repository.Repository

class ModuleFragment : Fragment() {

    val args: ModuleFragmentArgs by navArgs()
    private lateinit var binding: FragmentModuleBinding
    private val moduleAdapter: ModuleAdapter = ModuleAdapter()
    private val viewModel: ModuleViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentModuleBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        initObservers()
        viewModel.setModule(args.moduleId)
        binding.btnCards.setOnClickListener {
            val action = ModuleFragmentDirections.actionModuleFragmentToCardsFragment(args.moduleId)
            view.findNavController().navigate(action)
        }
    }

    private fun initObservers() {
        viewModel.module.observe(viewLifecycleOwner) {
            moduleAdapter.setData(it.words)
            binding.txtModuleName.text = it.module.name
            moduleAdapter.notifyDataSetChanged()
        }
    }

    private fun initUi() {
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.rvModule.layoutManager = linearLayoutManager
        binding.rvModule.adapter = moduleAdapter
    }

}