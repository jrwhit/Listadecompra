package com.example.listadecompras

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream

val productsGlobal = mutableListOf<Product>()

fun Bitmap.toByteArray(): ByteArray {
    val stream = ByteArrayOutputStream()

    this.compress(android.graphics.Bitmap.CompressFormat.PNG, 0, stream)

    return stream.toByteArray()
}

fun ByteArray.toBitmap(): Bitmap {
    return BitmapFactory.decodeByteArray(this, 0, this.size)
}