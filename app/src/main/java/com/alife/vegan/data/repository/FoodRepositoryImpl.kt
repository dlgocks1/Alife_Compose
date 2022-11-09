package com.alife.vegan.data.repository

import com.alife.vegan.domain.repository.FoodRepository
import com.alife.vegan.network.AlifeService
import com.alife.vegan.network.response.GetFoodByPriceResponse
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FoodRepositoryImpl @Inject constructor(
    private val service: AlifeService
) : FoodRepository {
    override fun getDietList(): Flow<GetFoodByPriceResponse> = flow {
        val response = service.getDietList()
        response.suspendOnSuccess {
            emit(data)
        }
    }

    override fun searchFood(productName: String): Flow<GetFoodByPriceResponse> = flow {
        val response = service.searchFood(productName)
        response.suspendOnSuccess {
            emit(data)
        }
    }
}