package com.alife.vegan.data.repository

import androidx.lifecycle.Transformations.map
import com.alife.vegan.domain.repository.RegisterDietRepository
import com.alife.vegan.network.AlifeService
import com.alife.vegan.network.request.RegisterDietRequest
import com.alife.vegan.network.request.RegisterDietRequestItem
import com.alife.vegan.network.response.GetFoodByPriceResponse
import com.skydoves.sandwich.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class RegisterDietRepositoryImpl @Inject constructor(
    private val alifeService: AlifeService
) : RegisterDietRepository {

    override fun getFoodByPrice(
        price: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
    ): Flow<GetFoodByPriceResponse> = flow {
        val response = alifeService.getFoodByPrice(price = price)
        response.suspendOnSuccess {
            emit(data)
        }.onError {
            throw Error(message().toString())
        }.onFailure {
            throw Error(message().toString())
        }.onException {
            throw Error(message().toString())
        }
    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(Dispatchers.IO)

    override fun registerDiet(params: ArrayList<RegisterDietRequestItem>) = flow {
        val response = alifeService.registerDiet(params = params)
        response.suspendOnSuccess {
            emit(data)
        }.onError {
            throw Error(message().toString())
        }.onFailure {
            throw Error(message().toString())
        }.onException {
            throw Error(message().toString())
        }
    }

}