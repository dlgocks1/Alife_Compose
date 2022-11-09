package com.alife.vegan.network.response

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class GetFoodByPriceResponseItem(
    @Json(name = "amount")
    val amount: Double,
    @Json(name = "calory")
    val calory: Double,
    @Json(name = "carbohydrate")
    val carbohydrate: Double,
    @Json(name = "cholesterol")
    val cholesterol: Double,
    @Json(name = "company")
    val company: String,
    @Json(name = "cooking_type")
    val cooking_type: String,
    @Json(name = "fat")
    val fat: Double,
    @Json(name = "id")
    val id: Int,
    @Json(name = "ingredient")
    val ingredient: String,
    @Json(name = "price")
    val price: Int,
    @Json(name = "primary_type")
    val primary_type: String,
    @Json(name = "product_category")
    val product_category: String?,
    @Json(name = "product_image")
    val product_image: String,
    @Json(name = "product_name")
    val product_name: String,
    @Json(name = "protein")
    val protein: Double,
    @Json(name = "sat_fat")
    val satFat: Double,
    @Json(name = "secondary_type")
    val secondary_type: String,
    @Json(name = "serving_size")
    val serving_size: Double,
    @Json(name = "sodium")
    val sodium: Double,
    @Json(name = "specific")
    val specific: String?,
    @Json(name = "sugar")
    val sugar: Double,
    @Json(name = "trans_fat")
    val trans_fat: Double,
    @Json(name = "vegan_option")
    val vegan_option: String,
    var isSelected: Boolean = false,
    var time: Int = 0,
    @Json(name = "wtime")
    val wtime: String? = "아침",
) : Parcelable