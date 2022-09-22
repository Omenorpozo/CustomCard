package com.omar.mobiletest.ui.feature.cards

import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omar.mobiletest.ui.feature.cards.model.Card
import com.omar.mobiletest.ui.utils.GetCards
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ListCardsViewModel : ViewModel() {

    private val _events = Channel<Event>()
    val events = _events.receiveAsFlow()

    private val _state = MutableStateFlow<State>(State.Idle)
    val state: StateFlow<State> = _state.asStateFlow()


    fun setUp(context: Context) {
        _state.value = State.Loading
        Handler(Looper.getMainLooper()).postDelayed({
            viewModelScope.launch {
                _events.send(Event.SetUpCards(GetCards.mockupCards(context)))
                _state.emit(State.Idle)
            }
        }, 2000)
    }
}

sealed interface Event {
    data class SetUpCards(val listCards: List<Card>) : Event
    object SetUpError : Event
}

sealed interface State {
    object Idle : State
    object Loading : State
}