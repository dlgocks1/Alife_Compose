package com.alife.vegan.network.request


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegisterDietRequestItem(
    @Json(name = "id")
    val id: Int,
    @Json(name = "wtime")
    val wtime: String
)