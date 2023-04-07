package com.samsungmalex.slotmachinetest.domain.model

import com.samsungmalex.slotmachinetest.R

enum class Items(val payoutMultiplier: Int, val probability: Double, val drawable: Int) {
    ORANGE(payoutMultiplier = 200, probability = 0.01, R.drawable.item0),
    STRAWBERRY(payoutMultiplier = 100, probability = 0.03, R.drawable.item1),
    GRAPE(payoutMultiplier = 50, probability = 0.05, R.drawable.item2),
    COCONUT(payoutMultiplier = 20, probability = 0.1, R.drawable.item3),
    PEACH(payoutMultiplier = 10, probability = 0.15, R.drawable.item4),
    BANANA(payoutMultiplier = 5, probability = 0.25, R.drawable.item5),
    KIWI(payoutMultiplier = 2, probability = 0.3, R.drawable.item6)
}