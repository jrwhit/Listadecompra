package com.example.listadecompras.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.listadecompras.*
import com.example.listadecompras.databinding.ActivityMainBinding
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.parseList
import org.jetbrains.anko.db.select
import org.jetbrains.anko.db.rowParser
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

            val item: Product = parent.getItemAtPosition(position) as Product

            database.use {
                delete("product", "id = {id}", "id" to item.id)
            }

            productAdapter.remove(item)

            true
        }
    }

    override fun onResume() {
        super.onResume()

        val adapter = binding.listViewProduct.adapter as ProductAdapter

        database.use {
            select("product").exec {
                val parser = rowParser {
                        id: Int,
                        name: String,
                        quantity: Int,
                        price: Double,
                        photo: ByteArray? -> "photo"

                    Product(id, price, name, quantity, photo?.toBitmap())
                }

                val products = parseList(parser)

                adapter.clear()

                adapter.addAll(products)

                val formatPTBr = NumberFormat.getCurrencyInstance(Locale("pr", "BR"))

                var sum = products.sumOf {
                    it.price * it.quantity
                }

                binding.labelAmount.text = "Total: ${formatPTBr.format(sum)}"
            }
        }
    }
}