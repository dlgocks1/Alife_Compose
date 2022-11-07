package com.alife.vegan.domain.repository

import com.alife.vegan.network.response.GetFoodByPriceResponse
import kotlinx.coroutines.flow.Flow

interface CalendarRepository {
    fun getDietList(): Flow<GetFoodByPriceResponse>
}