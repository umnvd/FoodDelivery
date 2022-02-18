package com.umnvd.fooddelivery.data

import com.umnvd.fooddelivery.models.Category
import com.umnvd.fooddelivery.models.Product

interface ProductsRepository {

    fun getAvailableCategories(cityName: String): List<Category>

    suspend fun getProductsByCategory(category: String): List<Product>

}

class TestProductsRepository: ProductsRepository {

    private val categories = listOf(
        Category("Beef", 0L),
        Category("Dessert", 1L),
        Category("Seafood", 2L),
        Category("Chicken", 3L),
        Category("Lamb", 4L),
        Category("Pork", 5L),
        Category("Starter", 6L),
        Category("Breakfast", 7L),
        Category("Goat", 8L),
        Category("Pasta", 9L),
        Category("Side", 10L),
        Category("Vegan", 11L),
    )
    private val availableCategories = mapOf(
        "Moscow" to categories.slice(0..2),
        "Nizhnevartovsk" to categories.slice(3..6),
        "Ulyanovsk" to categories.slice(7..11)
    )

    override fun getAvailableCategories(cityName: String): List<Category> {
        return availableCategories.getValue(cityName)
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