package com.umnvd.fooddelivery.data

interface CitiesRepository {

    fun getCities(): List<String>

}

class InMemoryCitiesRepository : CitiesRepository {

    private val cities = listOf("Moscow", "Nizhnevartovsk", "Ulyanovsk")

    override fun getCities(): List<String> = cities

}