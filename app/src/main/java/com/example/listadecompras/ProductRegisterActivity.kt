package com.example.listadecompras

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.listadecompras.databinding.ActivityProductRegisterBinding
import java.util.jar.Manifest

class ProductRegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductRegisterBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private val REQUEST_CODE = 13
    private val IMAGE_CHOOSE = 1000;
    private val PERMISSION_CODE = 1001;

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

        binding.imageviewProduct.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                print("aqui poha 4");
                if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_DENIED){
                    val permissions = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    requestPermissions(permissions, PERMISSION_CODE)
                } else{
                    chooseImageGallery();
                }
            }else{
                print("aqui poha 3");
                chooseImageGallery();
            }
        }

    }

    private fun chooseImageGallery() {
        val intent = Intent(Intent.ACTION_PICK)

        intent.type = "image/*"

        startActivityForResult(intent, IMAGE_CHOOSE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode) {
            PERMISSION_CODE -> {
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    chooseImageGallery()
                }else{
                    Toast.makeText(this, "Permissão negada", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        print("aqui poha 1");
        if(requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){
            print("aqui poha 2");
            binding.imageviewProduct.setImageURI(data?.data)
        }
    }
}