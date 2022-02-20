package com.example.listadecompras

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val productsListView = findViewById<ListView>(R.id.list_view_product)
        val txtField = findViewById<EditText>(R.id.txt_product)
        val btnInsert = findViewById<Button>(R.id.btn_insert)

        val adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1
        )

        productsListView.adapter = adapter

        btnInsert.setOnClickListener {
            val product = txtField.text.toString()

            if(product.isNotBlank()){
                txtField.text.clear()
                adapter.add(product)
            } else txtField.error = "Preencha um valor"
        }

        productsListView.setOnItemLongClickListener {
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long ->

            val item = parent.getItemAtPosition(position)

            adapter.remove(item as String?)

            true
        }
    }
}