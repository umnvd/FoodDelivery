package com.umnvd.fooddelivery

import com.umnvd.fooddelivery.data.InMemoryAdsRepository
import com.umnvd.fooddelivery.data.InMemoryCitiesRepository
import com.umnvd.fooddelivery.data.ProductsRepositoryImpl
import com.umnvd.fooddelivery.data.products.BASE_URL
import com.umnvd.fooddelivery.data.products.ProductsApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SingletonDependencies {

    private var dependencies: MutableList<Any> = mutableListOf()

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        dependencies.add(InMemoryAdsRepository())
        dependencies.add(InMemoryCitiesRepository())
        dependencies.add(
            ProductsRepositoryImpl(
                retrofit.create(ProductsApiService::class.java)
            )
        )
    }

    fun getDependencies(): List<Any> {
        return dependencies
    }
}