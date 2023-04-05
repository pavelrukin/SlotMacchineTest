package com.samsungmalex.slotmachinetest.domain.model

enum class Items(val payoutMultiplier: Int, val probability: Double) {
    ORANGE(payoutMultiplier = 200, probability = 0.01),
    STRAWBERRY(payoutMultiplier = 100, probability = 0.03),
    GRAPE(payoutMultiplier = 50, probability = 0.05),
    COCONUT(payoutMultiplier = 20, probability = 0.1),
    PEACH(payoutMultiplier = 10, probability = 0.15),
    BANANA(payoutMultiplier = 5, probability = 0.25),
    KIWI(payoutMultiplier = 2, probability = 0.3)
}