package com.samsungmalex.slotmachinetest.presentation.screens.game

import android.util.Log
import androidx.lifecycle.*
import com.samsungmalex.slotmachinetest.domain.model.Items
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

    private var _imageItem1 = MutableLiveData<Int>(0)
    val imageItem1: LiveData<Int> = _imageItem1
    private var _imageItem2 = MutableLiveData(0)
    val imageItem2: LiveData<Int> = _imageItem2
    private var _imageItem3 = MutableLiveData(0)
    val imageItem3: LiveData<Int> = _imageItem3


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
            delay(500)
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

        viewModelScope.launch() {
            gameRepository.updateCredits(credits.value!! - bet.value!!)
            val result = gameRepository.spin(bet.value!!)
            _win.value = result.payout
            _imageItem1.value = result.items[0].drawable
            _imageItem2.value = result.items[1].drawable
            _imageItem3.value = result.items[2].drawable

        }

    }

}

