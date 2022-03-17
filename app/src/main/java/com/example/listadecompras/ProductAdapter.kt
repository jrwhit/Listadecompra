package com.example.listadecompras

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import com.example.listadecompras.databinding.HorizontalProductItemBinding
import com.example.listadecompras.view.MainActivity
import com.example.listadecompras.view.ProductRegisterActivity
import java.text.NumberFormat
import java.util.*

class ProductAdapter(context: Context)
    : ArrayAdapter<Product>(
        context,
        R.layout.horizontal_product_item,
    ) {

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

        val product = getItem(position)
        val priceFormat = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))


        val title = view.findViewById<TextView>(R.id.text_view_title)
        val quantity = view.findViewById<TextView>(R.id.text_view_quantity)
        val price = view.findViewById<TextView>(R.id.text_view_price)
        val image = view.findViewById<ImageView>(R.id.imageview_product)
        val button = view.findViewById<Button>(R.id.btn_insert)

        title.text = if (product?.title != null) product.title else ""
        quantity.text = if(product?.quantity != null) product.quantity.toString() else ""
        price.text = if(product?.price != null) priceFormat.format(product.price) else ""

        if(product?.image != null) image.setImageBitmap(product.image)

        button.setOnClickListener {
            val intent = Intent(context, ProductRegisterActivity::class.java)

            if(product != null) {
                intent.putExtra("id", product.id)
                intent.putExtra("title", product.title)
                intent.putExtra("price", product.price)
                intent.putExtra("quantity", product.quantity)
                intent.putExtra("image", product.image)
            }

            view.context.startActivity(intent)
        }

        return view
    }
}