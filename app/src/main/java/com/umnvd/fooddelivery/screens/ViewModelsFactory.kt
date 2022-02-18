package com.umnvd.fooddelivery.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.umnvd.fooddelivery.SingletonDependencies
import java.lang.reflect.Constructor

class ViewModelsFactory : ViewModelProvider.Factory {

    private val dependencies = SingletonDependencies.getDependencies()

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val constructor = modelClass.constructors.maxByOrNull { it.parameterTypes.size }!!
        val arguments = findDependencies(constructor, dependencies)
        return constructor.newInstance(*arguments.toTypedArray()) as T
    }

    private fun findDependencies(constructor: Constructor<*>, dependencies: List<Any>): List<Any> {
        val args = mutableListOf<Any>()
        constructor.parameterTypes.forEach { parameterClass ->
            val dependency = dependencies.first { parameterClass.isAssignableFrom(it.javaClass) }
            args.add(dependency)
        }
        return args
    }

}