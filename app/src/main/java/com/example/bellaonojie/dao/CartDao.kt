package com.example.bellaonojie.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.bellaonojie.models.Cart

@Dao
interface CartDao {

    @Query("SELECT * FROM cart_table WHERE id = :id")
    fun getUserCart(id: Long): LiveData<List<Cart>>

    @Query("SELECT * FROM cart_table")
    fun getCartItems(): LiveData<List<Cart>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItemToCart(cart: Cart)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartItems(cartItems: List<Cart>)

    @Update
    suspend fun update(cart: Cart)

    @Delete
    suspend fun deleteCartItem(cart: Cart)

    @Query("DELETE FROM cart_table")
    suspend fun deleteAll()
}