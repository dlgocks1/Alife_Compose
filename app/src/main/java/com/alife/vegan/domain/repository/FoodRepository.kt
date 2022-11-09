package com.alife.vegan.domain.repository

import com.alife.vegan.network.response.GetFoodByPriceResponse
import kotlinx.coroutines.flow.Flow

interface FoodRepository {
    fun getDietList(): Flow<GetFoodByPriceResponse>
    fun searchFood(productName: String): Flow<GetFoodByPriceResponse>
}