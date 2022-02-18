package com.umnvd.fooddelivery.data.products

import com.google.gson.annotations.SerializedName

data class ProductApiResponse<T>(
    @SerializedName("meals")
    val responseArray: List<T>
)

data class ProductApiModel(
    @SerializedName("strMeal")
    val name: String,
    @SerializedName("strMealThumb")
    val imageUrl: String,
    @SerializedName("idMeal")
    val id: Long
)

data class ProductDetailsApiModel(
    @SerializedName("strInstructions")
    val description: String
)
