package com.notescollection.app.notes.presentation.landing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LandingViewModel : ViewModel() {

    private val eventChannel = Channel<LandingEvent>()
    val events = eventChannel.receiveAsFlow()

    fun onAction(action: LandingAction) {
        when (action) {
            LandingAction.GetStartedClick -> {
                viewModelScope.launch {
                    eventChannel.send(LandingEvent.GetStartedClick)
                }
            }

            LandingAction.LoginClick -> {
                viewModelScope.launch {
                    eventChannel.send(LandingEvent.LoginClick)
                }
            }
        }
    }
}