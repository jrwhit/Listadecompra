package com.example.listadecompras.view

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.listadecompras.Product
import com.example.listadecompras.database
import com.example.listadecompras.databinding.ActivityProductRegisterBinding
import com.example.listadecompras.toByteArray
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.toast

class ProductRegisterActivity : AppCompatActivity() {
    private val REQUEST_CODE = 1000
    private val IMAGE_CHOOSE = 1000
    private var imageBitMap: Bitmap? = null
    private var product: Product? = null


    private val binding by lazy {
        ActivityProductRegisterBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val b = intent.extras
        if(b != null){
            val extra = Product(
                b.get("id") as Int,
                b.get("price") as Double,
                b.get("title") as String,
                b.get("quantity") as Int,
            )

            product = extra

            binding.txtNameProduct.setText(product!!.title)
            binding.txtPrice.setText(product!!.price.toString())
            binding.txtQuantity.setText(product!!.quantity.toString())
            binding.imageviewProduct.setImageBitmap(product!!.image)
        }

        binding.btnInsert.setOnClickListener {

            val title = binding.txtNameProduct.text.toString()
            val quantity = binding.txtQuantity.text.toString()
            val price = binding.txtPrice.text.toString()

            if(title.isNotEmpty() && quantity.isNotEmpty() && price.isNotEmpty()) {

                if(product != null) {
                    val values = ContentValues()
                    values.put("name", title)
                    values.put("quantity", quantity.toInt())
                    values.put("price", price.toDouble())
                    values.put("photo", imageBitMap?.toByteArray())

                    database.use {
                        val up = update("product",
                            values,
                            "id=?",
                            arrayOf(product!!.id.toString())
                        )

                        if(up != 0) {
                            toast("Update foi um sucesso")
                        }else{
                            toast("Falha no update")
                        }
                    }
                }else{
                    database.use {
                        val responseDatabase = insert(
                            "product",
                            "name" to title,
                            "quantity" to quantity.toInt(),
                            "price" to price.toDouble(),
                            "photo" to imageBitMap?.toByteArray(),
                        )

                        if(responseDatabase != -1L ) {
                            toast("Item inserido com sucesso")

                            binding.txtNameProduct.text.clear()
                            binding.txtQuantity.text.clear()
                            binding.txtPrice.text.clear()
                        }else{
                            toast("Error ao inserir produto")
                        }
                    }
                }

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