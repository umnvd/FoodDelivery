package com.umnvd.fooddelivery.screens.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umnvd.fooddelivery.data.*
import com.umnvd.fooddelivery.models.Ad
import com.umnvd.fooddelivery.models.Product
import com.umnvd.fooddelivery.screens.extentions.share
import kotlinx.coroutines.launch

class MenuViewModel : ViewModel() {

    private val adsRepository: AdsRepository = InMemoryAdsRepository()
    private val citiesRepository: CitiesRepository = InMemoryCitiesRepository()
    private val productsRepository: ProductsRepository = TestProductsRepository()

    private val _ads: MutableLiveData<List<Ad>> = MutableLiveData(adsRepository.getAds())
    val ads = _ads.share()

    private val _cities: MutableLiveData<List<String>> = MutableLiveData(citiesRepository.getCities())
    val cities = _cities.share()

    private val _categories: MutableLiveData<List<String>>  = MutableLiveData()
    val categories = _categories.share()


    private val productsByCategory: MutableMap<String, MutableLiveData<List<Product>>> = hashMapOf()

    fun setCurrentCity(cityName: String) {
        _categories.value = productsRepository.getAvailableCategories(cityName)
    }

    fun getProductsByCategory(category: String): LiveData<List<Product>> {
        return productsByCategory.getOrPut(category, ::MutableLiveData)
    }

    fun loadProductsByCategory(category: String) {
        viewModelScope.launch {
            val products = productsRepository.getProductsByCategory(category)
            productsByCategory.getOrPut(category, ::MutableLiveData).value = products
        }
    }

}