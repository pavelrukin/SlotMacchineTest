package com.samsungmalex.slotmachinetest.presentation.screens.game

import android.util.Log
import androidx.lifecycle.*
import com.samsungmalex.slotmachinetest.domain.model.SpinResult
import com.samsungmalex.slotmachinetest.domain.repository.GameRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

import kotlinx.coroutines.launch

class GameViewModel(private val gameRepository: GameRepository) : ViewModel() {

    private val _imagePayTable = MutableLiveData<ButtonState>(ButtonState.DEFAULT)
    private val _imageBetOne = MutableLiveData<ButtonState>(ButtonState.DEFAULT)
    private val _imageBetMax = MutableLiveData<ButtonState>(ButtonState.DEFAULT)
    private val _imageSpin = MutableLiveData<ButtonState>(ButtonState.DEFAULT)

    val imagePayTable: LiveData<ButtonState>
        get() = _imagePayTable

    val imageBetOne: LiveData<ButtonState>
        get() = _imageBetOne

    val imageBetMax: LiveData<ButtonState>
        get() = _imageBetMax

    val imageSpin: LiveData<ButtonState>
        get() = _imageSpin


    private val _bet = MutableLiveData(10)
    val bet: LiveData<Int> = _bet

    private val _credits = MutableLiveData(0)
    val credits: LiveData<Int> = _credits

    private val _win = MutableLiveData(0)
    val win: LiveData<Int> = _win

    init {
        getValueCredit()
    }

    fun imagePayTableClick() {
        viewModelScope.launch {
            _imagePayTable.value = ButtonState.SELECTED
            delay(500)
            _imagePayTable.value = ButtonState.DEFAULT
        }
    }

    private fun getValueCredit() {
        viewModelScope.launch {
            gameRepository.getCurrentCredits().collectLatest { creditsValue ->
                _credits.value = creditsValue
            }
        }
    }


    fun imageBetOneClick() {
        setBetOneValue()
        viewModelScope.launch {
            _imageBetOne.value = ButtonState.SELECTED
            delay(500)
            _imageBetOne.value = ButtonState.DEFAULT
        }

    }

    private fun setBetOneValue() {
        if (bet.value!! < 30) {
            _bet.value = bet.value?.plus(10)
        }
    }

    fun imageBetMaxClick() {
        viewModelScope.launch {
            setBetValueMax()
            _imageBetMax.value = ButtonState.SELECTED
            delay(500)
            _imageBetMax.value = ButtonState.DEFAULT
        }
    }

    private fun setBetValueMax() {
        _bet.value = 30
    }


    fun imageSpinClick() {
        viewModelScope.launch {
            _imageSpin.value = ButtonState.SELECTED
            delay(500)
            disableButtons()
            delay(4000)
            _imageSpin.value = ButtonState.DEFAULT
            defaultButtons()
            spin()
            setBetValue()
        }
    }

    private fun setBetValue() {
        when (bet.value) {
            10 -> _bet.value = 20
            20 -> _bet.value = 30
            30 -> _bet.value = 10
        }
    }

    private fun disableButtons() {
        _imagePayTable.value = ButtonState.DISABLED
        _imageBetOne.value = ButtonState.DISABLED
        _imageBetMax.value = ButtonState.DISABLED
        //  _imageSpin.value = ButtonState.DISABLED
    }

    private fun defaultButtons() {
        _imagePayTable.value = ButtonState.DEFAULT
        _imageBetOne.value = ButtonState.DEFAULT
        _imageBetMax.value = ButtonState.DEFAULT
        _imageSpin.value = ButtonState.DEFAULT
    }
    fun spin() {
        Log.d(javaClass.simpleName, "spin: start")
        viewModelScope.launch() {
            gameRepository.updateCredits(credits.value!! - bet.value!!)
            val result = gameRepository.spin(bet.value!!)
            Log.d(javaClass.simpleName, "spinViewModel: ${result} ")
            _win.value = result.payout

        }

        Log.d(javaClass.simpleName, "spin: end~!@!@!@")
        Log.d(javaClass.simpleName, "spin: ${bet.value}")


    }

}

