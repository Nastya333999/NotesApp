package com.notescollection.app.notes.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {

    private val _navigationEvent = MutableSharedFlow<Unit>(replay = 0)
    val navigationEvent: SharedFlow<Unit> = _navigationEvent

    init {
        viewModelScope.launch {
            delay(2000)
            _navigationEvent.emit(Unit)
        }
    }
}