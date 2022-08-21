package com.anastasia.develop.learneasy.ui.cards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.anastasia.develop.learneasy.databinding.FragmentCardsBinding
import com.anastasia.develop.learneasy.ui.create_module.CreateNewModelViewModel
import com.anastasia.develop.learneasy.ui.module.ModuleFragmentArgs
import com.yuyakaido.android.cardstackview.CardStackLayoutManager

class CardsFragment : Fragment() {

    private lateinit var binding: FragmentCardsBinding
    private val cardsAdapter: CardsAdapter = CardsAdapter()
    private val viewModel: CardsViewModel by viewModels()
    val args: CardsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCardsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        initObservers()
        viewModel.setModule(args.moduleId)
    }

    private fun initUi() {
        val layoutManager = CardStackLayoutManager(requireContext())
        layoutManager.setSwipeThreshold(0.1f)
        binding.rvCards.layoutManager = layoutManager
        binding.rvCards.adapter = cardsAdapter
    }

    private fun initObservers() {
        viewModel.words.observe(viewLifecycleOwner) {
            cardsAdapter.setData(it)
            cardsAdapter.notifyDataSetChanged()
        }
    }

}