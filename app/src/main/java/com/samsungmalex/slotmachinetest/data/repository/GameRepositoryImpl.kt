package com.samsungmalex.slotmachinetest.data.repository

import android.util.Log
import com.samsungmalex.slotmachinetest.data.data_store.DataStoreManger
import com.samsungmalex.slotmachinetest.domain.model.SpinResult
import com.samsungmalex.slotmachinetest.domain.repository.GameRepository
import com.samsungmalex.slotmachinetest.domain.model.Items
import kotlinx.coroutines.flow.Flow

class GameRepositoryImpl(private val dataStoreManger: DataStoreManger) : GameRepository {


    override suspend fun getCurrentCredits(): Flow<Int> =
        dataStoreManger.getCreditValuePreferences()

    override suspend fun updateCredits(amount: Int?) =
        dataStoreManger.saveCreditValuePreference(amount)

    override suspend fun spin(bet: Int): SpinResult {
        val items = Items.values().toList()
        val result = mutableListOf<Items>()
        for (i in 0 until 3) {
            var randomItem = items[0]
            var randomNum = Math.random()
            for (j in 1 until items.size) {
                if (randomNum < items[j].probability) {
                    randomItem = items[j]
                    break
                } else {
                    randomNum -= items[j].probability
                }
            }
            result.add(randomItem)
        }
        val payout = calculatePayout(result, bet)

        val credits = dataStoreManger.getCreditValuePreferencesInt()
        Log.i(javaClass.simpleName, "spincred: $credits")
        if (payout != 0) {
            if (credits != null) {
                updateCredits(credits + payout)
            }
        }
        return SpinResult(result, payout)

    }

    private fun calculatePayout(items: List<Items>, bet: Int): Int {
        return when (items) {
            listOf(Items.ORANGE, Items.ORANGE, Items.ORANGE) -> {
                Items.ORANGE.payoutMultiplier * bet
            }
            listOf(Items.KIWI, Items.KIWI, Items.KIWI) -> {
                Items.KIWI.payoutMultiplier * bet
            }
            listOf(Items.BANANA, Items.BANANA, Items.BANANA) -> {
                Items.BANANA.payoutMultiplier * bet
            }
            listOf(Items.STRAWBERRY, Items.STRAWBERRY, Items.STRAWBERRY) -> {
                Items.STRAWBERRY.payoutMultiplier * bet
            }
            listOf(Items.GRAPE, Items.GRAPE, Items.GRAPE) -> {
                Items.GRAPE.payoutMultiplier * bet
            }
            listOf(Items.COCONUT, Items.COCONUT, Items.COCONUT) -> {
                Items.COCONUT.payoutMultiplier * bet
            }
            listOf(Items.PEACH, Items.PEACH, Items.PEACH) -> {
                Items.PEACH.payoutMultiplier * bet
            }
            else -> {
                0
            }
        }
    }


}



