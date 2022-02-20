package com.example.listadecompras

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class ProductAdapter(context: Context)
    : ArrayAdapter<Product>(
        context,
        R.layout.horizontal_product_item,
    ){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val product = getItem(position) as Product

        if (convertView != null) view = convertView
        else
            view = LayoutInflater.from(context)
                .inflate(
                    R.layout.horizontal_product_item,
                    parent,
                    false,
                )


        val title = view.findViewById<TextView>(R.id.label_title)
        val quantity = view.findViewById<TextView>(R.id.txt_quantity)
        val price = view.findViewById<TextView>(R.id.label_amount)
        val image = view.findViewById<ImageView>(R.id.imageview_product)

        title.text = product.title
        quantity.text = product.quantity.toString()
        price.text = product.price.toString()

        if(product.image != null) image.setImageBitmap(product.image)

        return view
    }
}