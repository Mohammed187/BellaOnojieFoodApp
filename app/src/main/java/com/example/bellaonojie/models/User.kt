package com.example.bellaonojie.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "users_table")
data class User(
        @PrimaryKey(autoGenerate = true) val id: Long,
        @ColumnInfo(name = "username") val username: String,
        @ColumnInfo(name = "email") val email: String,
        @ColumnInfo(name = "password") val password: String,
        @ColumnInfo(name = "image") val image: String,
        @ColumnInfo(name = "phone") val phone: String,
        @ColumnInfo(name = "address") val address: String,
        @ColumnInfo(name = "created") val created: Long,
        @ColumnInfo(name = "cartId") val cart_id: Long?,
        @ColumnInfo(name = "cartTotal") val cart_total: Int
) : Parcelable
