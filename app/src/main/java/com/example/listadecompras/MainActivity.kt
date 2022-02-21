package com.example.listadecompras

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.listadecompras.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val productAdapter = ProductAdapter(this)

        productAdapter.addAll(productsGlobal)

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

//            productAdapter.remove(binding.labelAmount as String?)

            true
        }
    }
}