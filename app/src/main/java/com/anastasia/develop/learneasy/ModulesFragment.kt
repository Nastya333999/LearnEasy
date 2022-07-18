package com.anastasia.develop.learneasy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.anastasia.develop.learneasy.databinding.FragmentStartBinding

class ModulesFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding
    private val startAdapter: ModulesAdapter = ModulesAdapter()

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

        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.rvAllModules.layoutManager = linearLayoutManager
        binding.rvAllModules.adapter = startAdapter

        startAdapter.setData(Repository.repository.getModules())
        startAdapter.onShowClickListener = {
            // переход на можуль

        }

        binding.btnShowAll.setOnClickListener {
            view.findNavController().navigate(R.id.action_startFragment_to_showAllFragment)
        }
    }

}