package com.example.bellaonojie.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.bellaonojie.BellaApplication
import com.example.bellaonojie.models.Cart
import kotlinx.coroutines.launch

class CartViewModel(
        application: BellaApplication
) : AndroidViewModel(application) {

    private val repository = application.cartRepository

    val allCartItems: LiveData<List<Cart>> = repository.cartItems

    private val _cartAlert = MutableLiveData<Boolean>()
    val cartAlert: LiveData<Boolean?>
        get() = _cartAlert

    fun insert(cart: Cart) = viewModelScope.launch {
        repository.insert(cart)
        itemAddedToCart()
        Log.d("Cart", "insert: Called")
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()

        Log.d("Cart", "Delete All: Called")
    }

    fun deleteCartItem(cart: Cart) = viewModelScope.launch {
        repository.deleteCartItem(cart)

        Log.d("Cart", "Delete ${cart.name}: Called")
    }

    private fun itemAddedToCart() {
        _cartAlert.value = true
    }

    fun itemAddedDone() {
        _cartAlert.value = false
    }

    private fun updateCartItem(cart: Cart) = viewModelScope.launch {
        repository.update(cart)
        Log.d("Cart", "updateCartItem: Called")
    }

    fun increaseQ(cart: Cart) = viewModelScope.launch {
        val newCart =
                Cart(cart.id, cart.name, cart.price, cart.image, cart.category, cart.quantity++)
        Log.d("Cart", "increaseQ: ${newCart.quantity}")
        updateCartItem(newCart)
    }

    fun decreaseQ(cart: Cart) = viewModelScope.launch {
        val newCart =
                Cart(cart.id, cart.name, cart.price, cart.image, cart.category, cart.quantity--)
        Log.d("Cart", "decreaseQ: ${cart.quantity}")
        updateCartItem(newCart)
    }

}

class CartViewModelFactory(
        private val application: BellaApplication
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CartViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}