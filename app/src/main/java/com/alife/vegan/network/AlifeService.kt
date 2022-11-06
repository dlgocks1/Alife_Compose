package com.alife.vegan.network

import com.alife.vegan.network.response.GetFoodByPriceResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface AlifeService {

    @GET("/userinfo/cut_by_price")
    suspend fun getFoodByPrice(@Query("price") price: Int)
        : ApiResponse<GetFoodByPriceResponse>


}