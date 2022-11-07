package com.alife.vegan.network

import com.alife.vegan.network.request.RegisterDietRequest
import com.alife.vegan.network.request.RegisterDietRequestItem
import com.alife.vegan.network.response.GetFoodByPriceResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AlifeService {

    @GET("/userinfo/cut_by_price/")
    suspend fun getFoodByPrice(@Query("price") price: Int)
        : ApiResponse<GetFoodByPriceResponse>

    @POST("/userinfo/meal_by_client/")
    suspend fun registerDiet(
        @Body params: ArrayList<RegisterDietRequestItem>
    ): ApiResponse<GetFoodByPriceResponse>

    @POST("/userinfo/get_meal")
    suspend fun getDietList(): ApiResponse<GetFoodByPriceResponse>

}