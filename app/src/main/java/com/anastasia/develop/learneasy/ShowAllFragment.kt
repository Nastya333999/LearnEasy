package com.anastasia.develop.learneasy

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.anastasia.develop.learneasy.data.Repository
import com.anastasia.develop.learneasy.databinding.FragmentShowAllBinding

class ShowAllFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    lateinit var binding: FragmentShowAllBinding
    private val showAdapter: ShowAdapter = ShowAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShowAllBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.rvShowAll.layoutManager = linearLayoutManager
        binding.rvShowAll.adapter = showAdapter

        showAdapter.onDeleteClickListener = {
            Repository.repository.deleteWord(it)
            showAdapter.setData(Repository.repository.getAllWords())
            showAdapter.notifyDataSetChanged()
        }

        val words = Repository.repository.getAllWords()
        Log.d("line", words.toString())
        showAdapter.setData(words)
    }

}