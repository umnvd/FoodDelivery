package com.umnvd.fooddelivery.screens.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umnvd.fooddelivery.data.AdsRepository
import com.umnvd.fooddelivery.data.CitiesRepository
import com.umnvd.fooddelivery.data.ProductsRepository
import com.umnvd.fooddelivery.models.Ad
import com.umnvd.fooddelivery.models.Category
import com.umnvd.fooddelivery.models.Product
import com.umnvd.fooddelivery.screens.extentions.share
import kotlinx.coroutines.launch

class MenuViewModel(
    adsRepository: AdsRepository,
    citiesRepository: CitiesRepository,
    private val productsRepository: ProductsRepository
) : ViewModel() {

    private val _ads: MutableLiveData<List<Ad>> = MutableLiveData(adsRepository.getAds())
    val ads = _ads.share()

    private val _cities: MutableLiveData<List<String>> =
        MutableLiveData(citiesRepository.getCities())
    val cities = _cities.share()

    private val _categories: MutableLiveData<List<Category>> = MutableLiveData()
    val categories = _categories.share()


    private val productsByCategory: MutableMap<Category, MutableLiveData<List<Product>>> =
        hashMapOf()

    fun setCurrentCity(cityName: String) {
        _categories.value = productsRepository.getAvailableCategories(cityName)
    }

    fun getProductsByCategory(category: Category): LiveData<List<Product>> {
        return productsByCategory.getOrPut(category, ::MutableLiveData)
    }

    fun loadProductsByCategory(category: Category) {
        viewModelScope.launch {
            val products = productsRepository.getProductsByCategory(category)
            productsByCategory.getOrPut(category, ::MutableLiveData).value = products
        }
    }

}