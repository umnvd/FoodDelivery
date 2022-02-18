package com.umnvd.fooddelivery.data.products

import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"
const val THUMBNAIL_PATH = "/preview"

interface ProductsApiService {

    @GET("filter.php")
    suspend fun getProductsByCategory(
        @Query("c") name: String
    ): ProductApiResponse<ProductApiModel>

    @GET("lookup.php")
    suspend fun getProductDetails(
        @Query("i") id: Long
    ): ProductApiResponse<ProductDetailsApiModel>

}