package com.umnvd.fooddelivery.screens.menu.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import com.umnvd.fooddelivery.R

class CitiesAdapter(
    context: Context,
) : ArrayAdapter<String>(context, 0) {

    private val inflater = LayoutInflater.from(context)
    private var cities: List<String> = listOf()

    fun setCities(newCities: List<String>) {
        cities = newCities
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): String? = cities.getOrNull(position)

    override fun getCount(): Int = cities.count()


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView
            ?: inflater.inflate(R.layout.item_city, parent, false)
        getItem(position)?.let { bind(view, it) }
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView
            ?: inflater.inflate(R.layout.item_city_dropdown, parent, false)
        getItem(position)?.let { bind(view, it) }
        return view
    }

    private fun bind(view: View, city: String) {
        view.findViewById<TextView>(R.id.itemCityName).text = city
    }
}
