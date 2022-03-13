package com.example.listadecompras

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

val Context.database: OrderDatabase get() = OrderDatabase.getInstance(this)

class OrderDatabase(context: Context) : ManagedSQLiteOpenHelper(ctx = context, name = "purchases.db", version = 1) {
    override fun onCreate(db: SQLiteDatabase?) {

        db?.createTable(
            "product",
            true,
            "id" to INTEGER + PRIMARY_KEY + UNIQUE,
            "name" to TEXT,
            "quantity" to INTEGER,
            "price" to REAL,
            "photo" to BLOB,
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable("product", true)
        onCreate(db)
    }

    companion object {
        private var instance: OrderDatabase? = null

        @Synchronized
        fun getInstance(ctx: Context): OrderDatabase {
            if(instance == null) instance = OrderDatabase(ctx.applicationContext)
            return instance!!
        }
    }
}