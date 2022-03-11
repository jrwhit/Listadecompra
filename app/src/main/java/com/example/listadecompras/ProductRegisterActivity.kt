package com.example.listadecompras

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.listadecompras.databinding.ActivityProductRegisterBinding

class ProductRegisterActivity : AppCompatActivity() {
    private val REQUEST_CODE = 1000
    private val IMAGE_CHOOSE = 1000
    private var imageBitMap: Bitmap? = null


    private val binding by lazy {
        ActivityProductRegisterBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnInsert.setOnClickListener {

            val title = binding.txtNameProduct.text.toString()
            val quantity = binding.txtQuantity.text.toString()
            val price = binding.txtPrice.text.toString()

            if(title.isNotEmpty() && quantity.isNotEmpty() && price.isNotEmpty()) {
                val product = Product("1", price.toDouble(), title, quantity.toInt(), imageBitMap)

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

        binding.imageviewProduct.setOnClickListener {
            chooseImageGallery()
        }

    }

    private fun chooseImageGallery() {

        val intent = Intent(Intent.ACTION_PICK)

        intent.type = "image/*"

        startActivityForResult(intent, IMAGE_CHOOSE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){
            val inputStream = data?.data?.let { contentResolver.openInputStream(it) }
            imageBitMap = BitmapFactory.decodeStream(inputStream)
            binding.imageviewProduct.setImageBitmap(imageBitMap)
        }
    }
}