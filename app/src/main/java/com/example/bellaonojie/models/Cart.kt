package com.example.bellaonojie.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_table")
data class Cart(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "price") val price: Int,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "quantity") var quantity: Int
)
