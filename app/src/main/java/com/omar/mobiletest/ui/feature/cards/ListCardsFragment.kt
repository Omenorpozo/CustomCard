package com.omar.mobiletest.ui.feature.cards

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.omar.mobiletest.R
import com.omar.mobiletest.databinding.FragmentListCardsBinding
import com.omar.mobiletest.ui.delegate.collectInLifeCycle
import com.omar.mobiletest.ui.dialog.LoadingDialog
import com.omar.mobiletest.ui.extensions.show
import com.omar.mobiletest.ui.extensions.viewBinding
import com.omar.mobiletest.ui.feature.cards.adapter.CardAdapter
import com.omar.mobiletest.ui.feature.cards.model.Card
import com.omar.mobiletest.ui.viewmodel.ViewModelFactory
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ListCardsFragment : Fragment(R.layout.fragment_list_cards)  {

    private val binding by viewBinding<FragmentListCardsBinding>()
    private val viewModel by viewModels<ListCardsViewModel> { ViewModelFactory }
    private lateinit var cardAdapter: CardAdapter
    private lateinit var loadingDialog: LoadingDialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        attachObservers()
        setAdapter()
        setUp()
    }

    private fun setAdapter() {
        cardAdapter = CardAdapter()
        with(binding) { cardsRv.adapter = cardAdapter }
    }

    private fun attachObservers() {
        viewModel.apply {
            events.onEach(::observeSetUp).launchIn(viewLifecycleOwner.lifecycleScope)
            state.collectInLifeCycle(viewLifecycleOwner, ::renderState)
        }
    }

    private fun setUp() {
        with(viewModel) { setUp(requireContext()) }
    }

    private fun addCards(cards: List<Card>) {
        cardAdapter.apply {
            itemList = cards
        }
    }

    private fun observeSetUp(event: Event) {
        when(event) {
            is Event.SetUpCards -> addCards(event.listCards)
            is Event.SetUpError -> {

            }
        }
    }

    private fun renderState(state: State) {
        when(state) {
            is State.Loading -> showLoading()
            else ->  dismissLoading()
        }
    }

    private fun showLoading() {
        if (::loadingDialog.isInitialized) loadingDialog.dismissAllowingStateLoss()
        loadingDialog = LoadingDialog.newInstance()
        loadingDialog.show(childFragmentManager)
    }

    private fun dismissLoading() {
        if (::loadingDialog.isInitialized) loadingDialog.dismissAllowingStateLoss()
    }
}