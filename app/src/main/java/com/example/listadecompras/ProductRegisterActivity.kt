package com.example.listadecompras

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.listadecompras.databinding.ActivityProductRegisterBinding

class ProductRegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnInsert.setOnClickListener {

            val title = binding.txtNameProduct.text.toString()
            val quantity = binding.txtQuantity.text.toString()
            val price = binding.txtPrice.text.toString()

            if(title.isNotEmpty() && quantity.isNotEmpty() && price.isNotEmpty()) {
                val product = Product("1", price.toDouble(), title, quantity.toInt())

                productsGlobal.add(product)

                binding.txtNameProduct.text.clear()
                binding.txtQuantity.text.clear()
                binding.txtPrice.text.clear()

            }else{
                binding.txtNameProduct.error = if(title.isEmpty())  "Digite o nome do produto"
                else null

                binding.txtQuantity.error =  if (quantity.isEmpty()) "Digite a quantidade"
                else null

                binding.txtPrice.error = if (price.isEmpty())  "Digite o preço do produto"
                else null
            }
        }
    }
}