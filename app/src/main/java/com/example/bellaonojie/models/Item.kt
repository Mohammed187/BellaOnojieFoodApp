package com.example.bellaonojie.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "menu_table")
data class Item(
        @PrimaryKey(autoGenerate = true) val id: Long,
        @ColumnInfo(name = "name") val name: String,
        @ColumnInfo(name = "price") val price: Int,
        @ColumnInfo(name = "image") val image: String,
        @ColumnInfo(name = "category") val category: String
) : Parcelable
