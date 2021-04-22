package com.example.bellaonojie.repository

import androidx.lifecycle.LiveData
import com.example.bellaonojie.dao.ItemDao
import com.example.bellaonojie.models.Item

class MenuRepository(private val itemDao: ItemDao) {

    val allMenuItems: LiveData<List<Item>> = itemDao.getAllMenu()

    suspend fun getItem(itemId: Long): Item? {
        return itemDao.getItemById(itemId)
    }

    suspend fun getMenuForCategory(category: String): LiveData<List<Item>> {
        return itemDao.getMenuForCategory(category)
    }
}