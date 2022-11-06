package com.alife.vegan.network.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
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
    val cookingType: String,
    @Json(name = "fat")
    val fat: Double,
    @Json(name = "id")
    val id: Int,
    @Json(name = "ingredient")
    val ingredient: String,
    @Json(name = "price")
    val price: Int,
    @Json(name = "primary_type")
    val primaryType: String,
    @Json(name = "product_category")
    val productCategory: Any,
    @Json(name = "product_image")
    val productImage: String,
    @Json(name = "product_name")
    val productName: String,
    @Json(name = "protein")
    val protein: Double,
    @Json(name = "sat_fat")
    val satFat: Double,
    @Json(name = "secondary_type")
    val secondaryType: String,
    @Json(name = "serving_size")
    val servingSize: Double,
    @Json(name = "sodium")
    val sodium: Double,
    @Json(name = "specific")
    val specific: Any,
    @Json(name = "sugar")
    val sugar: Double,
    @Json(name = "trans_fat")
    val transFat: Double,
    @Json(name = "vegan_option")
    val veganOption: String
)