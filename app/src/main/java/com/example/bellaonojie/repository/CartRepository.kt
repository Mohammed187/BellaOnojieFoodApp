package com.example.bellaonojie.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.bellaonojie.dao.CartDao
import com.example.bellaonojie.models.Cart

class CartRepository(private val cartDao: CartDao) {

    val cartItems: LiveData<List<Cart>> = cartDao.getCartItems()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(cart: Cart) {
        cartDao.insertItemToCart(cart)
    }

    suspend fun update(cart: Cart) {
        cartDao.update(cart)
    }

    suspend fun deleteCartItem(cart: Cart) {
        cartDao.deleteCartItem(cart)
    }

    suspend fun deleteAll() {
        cartDao.deleteAll()
    }

}