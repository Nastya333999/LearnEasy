package com.anastasia.develop.learneasy.ui.cards

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.anastasia.develop.learneasy.databinding.FragmentCardsBinding
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction

class CardsFragment : Fragment() {

    private lateinit var binding: FragmentCardsBinding
    private val cardsAdapter: CardsAdapter = CardsAdapter()
    private val viewModel: CardsViewModel by viewModels()
    val args: CardsFragmentArgs by navArgs()
    private var processStatus = 0

    private val swipeListener = object : CardStackListener {
        private var lastAppearedPosition = 0
        override fun onCardDragging(direction: Direction?, ratio: Float) {}
        override fun onCardDisappeared(view: View?, position: Int) {}
        override fun onCardRewound() {}
        override fun onCardCanceled() {}

        override fun onCardAppeared(view: View?, position: Int) {
            lastAppearedPosition = position
        }

        override fun onCardSwiped(direction: Direction?) {
            when (direction?.name) {
                "Right" -> viewModel.swipeRight(lastAppearedPosition)
                "Left" -> viewModel.swipeLeft(lastAppearedPosition)
            }
        }

    }

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
        val layoutManager = CardStackLayoutManager(requireContext(), swipeListener)
        layoutManager.setSwipeThreshold(0.1f)
        binding.rvCards.layoutManager = layoutManager
        binding.rvCards.adapter = cardsAdapter
    }

    private fun initObservers() {
        viewModel.words.observe(viewLifecycleOwner) {
            cardsAdapter.setData(it)
            cardsAdapter.notifyDataSetChanged()
        }
        viewModel.learnedWordsCount.observe(viewLifecycleOwner) {
            binding.txtKnownWords.text = it.toString()
        }
        viewModel.skippedWordsCount.observe(viewLifecycleOwner) {
            binding.txtUnknownWords.text = it.toString()
        }
        viewModel.swippedWordsCount.observe(viewLifecycleOwner) {
            binding.txtWordsDone.text = it.toString()
            binding.progressBar.progress = it
        }
        viewModel.allWordsCount.observe(viewLifecycleOwner) {
            binding.txtWordsInModule.text = it.toString()
            binding.progressBar.max = it
        }

    }
}