package com.samsungmalex.slotmachinetest.presentation.screens.main_menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainMenuViewModel : ViewModel() {

    private val _navigateToGame = MutableLiveData<Boolean>()
    val navigateToGame: LiveData<Boolean>
        get() = _navigateToGame

    private val _navigateToExit = MutableLiveData<Boolean>()
    val navigateToExit: LiveData<Boolean>
        get() = _navigateToExit

    fun onPlayButtonClicked() {
        _navigateToGame.value = true
    }

    fun onExitButtonClicked() {
        _navigateToExit.value = true
    }

    fun onNavigatedToGame() {
        _navigateToGame.value = false
    }

    fun onNavigatedToExit() {
        _navigateToExit.value = false
    }
}