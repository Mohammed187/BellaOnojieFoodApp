package com.example.bellaonojie.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.bellaonojie.BellaApplication
import com.example.bellaonojie.models.Item
import com.example.bellaonojie.dao.ItemDao
import com.example.bellaonojie.models.Cart
import com.example.bellaonojie.repository.MenuRepository
import kotlinx.coroutines.launch

class ItemDetailsViewModel(
        private val itemKey: Item, application: BellaApplication) : ViewModel() {

    private val repository = application.menuRepository

    private val _item = MutableLiveData<Item>()
    val item: LiveData<Item?>
        get() = _item

    private val _cart = MutableLiveData<Cart>()
    val cartItem: LiveData<Cart>
        get() = _cart

    init {
        getItemDetails()
        generateCart()
    }

    private fun getItemDetails() {
        viewModelScope.launch {
            getData()
        }
    }

    private suspend fun getData() {
        _item.value = repository.getItem(itemKey.id)
    }

    private fun generateCart() {
        viewModelScope.launch {
            initCartItem()
        }
    }

    private suspend fun initCartItem() {
        Log.d("Cart", "initCartItem: Called")
        getData()
        if (_item.value != null) {
            _cart.value = item.value?.let {
                Cart(
                        itemKey.id,
                        it.name,
                        it.price,
                        it.image,
                        it.category,
                        1,
                )
            }
        }
    }

    private val _navigateToMenuFragment = MutableLiveData<Boolean?>()

    /**
     * When true immediately navigate back to the [MenuFragment]
     */
    val navigateToMenuItems: LiveData<Boolean?>
        get() = _navigateToMenuFragment

    /**
     * Call this immediately after navigating to [MenuFragment]
     */
    fun doneNavigating() {
        _navigateToMenuFragment.value = null
    }

    fun onClose() {
        _navigateToMenuFragment.value = true
    }
}

class ItemDetailsViewModelFactory(
        private val itemKey: Item,
        private val application: BellaApplication,
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ItemDetailsViewModel::class.java)) {
            return ItemDetailsViewModel(itemKey, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}