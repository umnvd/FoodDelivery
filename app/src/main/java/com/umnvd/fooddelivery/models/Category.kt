package com.umnvd.fooddelivery.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    val name: String,
    val id: Long
): Parcelable
