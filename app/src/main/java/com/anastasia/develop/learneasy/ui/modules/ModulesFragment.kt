package com.anastasia.develop.learneasy.ui.modules

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.anastasia.develop.learneasy.R
import com.anastasia.develop.learneasy.data.Repository
import com.anastasia.develop.learneasy.databinding.FragmentStartBinding
import com.anastasia.develop.learneasy.ui.module.ModuleAdapter
import com.anastasia.develop.learneasy.ui.module.ModuleViewModel

class ModulesFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding
    private val startAdapter: ModulesAdapter = ModulesAdapter()
    private val viewModel: ModulesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        initObserver()
        initListeners()
    }

    private fun initListeners() {
        startAdapter.onShowClickListener = {
            val action = ModulesFragmentDirections.actionStartFragmentToModuleFragment(module = it)
            view?.findNavController()?.navigate(action)
        }

        binding.btnShowAll.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_startFragment_to_showAllFragment)
        }
    }

    private fun initObserver() {
        viewModel.modules.observe(viewLifecycleOwner) {
            startAdapter.setData(it)
        }
    }

    private fun initUi() {
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.rvAllModules.layoutManager = linearLayoutManager
        binding.rvAllModules.adapter = startAdapter
    }

}