package com.example.listadecompras

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.listadecompras.databinding.HorizontalProductItemBinding
import java.text.NumberFormat
import java.util.*

class ProductAdapter(context: Context)
    : ArrayAdapter<Product>(
        context,
        R.layout.horizontal_product_item,
    ){

    private lateinit var bindings: HorizontalProductItemBinding

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View

        if (convertView != null) view = convertView
        else
            view = LayoutInflater.from(context)
                .inflate(
                    R.layout.horizontal_product_item,
                    parent,
                    false,
                )

        bindings = HorizontalProductItemBinding.bind(view)

        val product = getItem(position)
        val priceFormat = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))


        val title = bindings.textViewTitle
        val quantity = bindings.textViewQuantity
        val price = bindings.textViewPrice
        val image = bindings.productImage

        title.text = if (product?.title != null) product.title else ""
        quantity.text = if(product?.quantity != null) product.quantity.toString() else ""
        price.text = if(product?.price != null) priceFormat.format(product.price) else ""

        if(product?.image != null) image.setImageBitmap(product.image)

        return view
    }
}