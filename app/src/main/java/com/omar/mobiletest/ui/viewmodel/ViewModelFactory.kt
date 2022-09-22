package com.omar.mobiletest.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.omar.mobiletest.ui.feature.cards.ListCardsViewModel

object ViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListCardsViewModel::class.java)) {
            return ListCardsViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}