package com.example.listadecompras

import android.graphics.Bitmap

data class Product (
    val id: String,
    val price: Double,
    val title: String,
    val quantity: Int,
    val image: Bitmap? = null,
)