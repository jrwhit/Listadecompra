package com.example.listadecompras

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import com.example.listadecompras.databinding.ActivityMainBinding
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val productAdapter = ProductAdapter(this)

        binding.listViewProduct.adapter = productAdapter

        binding.btnGoToInsertProduct.setOnClickListener {
            val intent = Intent(this, ProductRegisterActivity::class.java)

            startActivity(intent)
        }

        binding.listViewProduct.setOnItemLongClickListener {
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long ->

            val item = parent.getItemAtPosition(position)

            productAdapter.remove(item as Product)

            true
        }
    }

    override fun onResume() {
        super.onResume()

        val adapter = binding.listViewProduct.adapter as ProductAdapter

        adapter.clear()

        adapter.addAll(productsGlobal)

        val formatPTBr = NumberFormat.getCurrencyInstance(Locale("pr", "BR"))

        var sum = productsGlobal.sumOf {
            it.price * it.quantity
        }

        binding.labelAmount.text = "Total: ${formatPTBr.format(sum)}"
    }

    fun openPictures() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)

        intent.type = "image/*"

        val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            // Handle the returned Uri
        }
    }
}