package com.anastasia.develop.learneasy.ui.module

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.anastasia.develop.learneasy.databinding.FragmentModuleBinding

class ModuleFragment : Fragment() {

    val args: ModuleFragmentArgs by navArgs()
    private lateinit var binding: FragmentModuleBinding
    private val moduleAdapter: ModuleAdapter = ModuleAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentModuleBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.rvModule.layoutManager = linearLayoutManager
        binding.rvModule.adapter = moduleAdapter

        moduleAdapter.setData(args.module.words)
    }


}