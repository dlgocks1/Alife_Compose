package com.alife.vegan.domain.repository

import com.alife.vegan.network.response.GetFoodByPriceResponse
import kotlinx.coroutines.flow.Flow

interface RegisterDietRepository {
    fun getFoodByPrice(
        params: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit
    ): Flow<GetFoodByPriceResponse>
}