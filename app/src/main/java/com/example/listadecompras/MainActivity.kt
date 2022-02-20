package com.example.listadecompras

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val productsListView = findViewById<ListView>(R.id.list_view_product)
        val amount = findViewById<TextView>(R.id.label_amount)
        val goToRegister = findViewById<Button>(R.id.btn_go_to_insert_product)

        val adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1
        )

        productsListView.adapter = adapter

        goToRegister.setOnClickListener {
            val intent = Intent(this, ProductRegisterActivity::class.java)

            startActivity(intent)
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