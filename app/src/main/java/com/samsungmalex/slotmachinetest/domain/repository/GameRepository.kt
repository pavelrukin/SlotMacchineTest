package com.samsungmalex.slotmachinetest.domain.repository

import com.samsungmalex.slotmachinetest.domain.model.SpinResult
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    suspend fun getCurrentCredits(): Flow<Int>
    suspend fun updateCredits(amount: Int?)
    suspend fun spin(bet: Int): SpinResult

}
