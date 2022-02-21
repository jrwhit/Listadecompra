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

        val product = binding.txtNameProduct.text.toString()
        val quantity = binding.txtQuantity.text.toString()
        val price = binding.txtPrice.text.toString()

        if(product.isNotEmpty() && quantity.isNotEmpty() && price.isNotEmpty()) {

        }else{
            if(product.isEmpty()) binding.txtNameProduct.error = "Digite o nome do produto"
                else if (quantity.isEmpty()) binding.txtQuantity.error = "Digite a quantidade"
                    else binding.txtPrice.error = "Digite o preço do produto"
        }
    }
}