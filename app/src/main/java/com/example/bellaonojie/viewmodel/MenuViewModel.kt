package com.example.bellaonojie.viewmodel

import androidx.lifecycle.*
import com.example.bellaonojie.BellaApplication
import com.example.bellaonojie.models.Item
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class MenuViewModel(application: BellaApplication, category: String) :
        AndroidViewModel(application) {

    private val repository = application.menuRepository
    private val allItems: LiveData<List<Item>>

    lateinit var itemsByCat: LiveData<List<Item>>

    init {
        allItems = repository.allMenuItems
        getMenuByCategory(category)
    }

    private fun getMenuByCategory(category: String) = viewModelScope.launch {
        itemsByCat = repository.getMenuForCategory(category)
    }

    // Navigate to item Details.
    private val _navigateToItemDetail = MutableLiveData<Item?>()
    val navigateToItemDetail: LiveData<Item?>
        get() = _navigateToItemDetail

    fun onMenuItemClicked(item: Item) {
        _navigateToItemDetail.value = item
    }

    fun onItemDetailNavigated() {
        _navigateToItemDetail.value = null
    }

    // Navigate to Cart.
    private val _navigateToCart = MutableLiveData<Boolean?>()
    val navigateToCart: LiveData<Boolean?>
        get() = _navigateToCart

    fun onCartClicked() {
        _navigateToCart.value = true
    }

    fun onCartNavigated() {
        _navigateToCart.value = null
    }

}

class MenuItemsViewModelFactory(private val application: BellaApplication, private val ref: String) :
        ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MenuViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MenuViewModel(application, ref) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}