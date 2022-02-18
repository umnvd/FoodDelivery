package com.umnvd.fooddelivery.data

import android.util.Log
import com.umnvd.fooddelivery.models.Product

interface ProductsRepository {

    fun getAvailableCategories(cityName: String): List<String>

    suspend fun getProductsByCategory(category: String): List<Product>

}

class TestProductsRepository: ProductsRepository {

    private val categories = hashMapOf<String, List<String>>()

    init {
        categories["Moscow"] = listOf(
            "Beef",
            "Dessert",
            "Seafood"
        )

        categories["Nizhnevartovsk"] = listOf(
            "Chicken",
            "Lamb",
            "Pork",
            "Starter"
        )

        categories["Ulyanovsk"] = listOf(
            "Breakfast",
            "Goat",
            "Pasta",
            "Side",
            "Vegan"
        )
    }

    override fun getAvailableCategories(cityName: String): List<String> {
        return categories.getValue(cityName)
    }

    override suspend fun getProductsByCategory(category: String): List<Product> {
        val result = mutableListOf<Product>()
        for (i in category.indices) {
            result.add(Product(
                    category + i,
                "$category$i description...",
                "",
                i
                ))
        }
        return result
    }

}