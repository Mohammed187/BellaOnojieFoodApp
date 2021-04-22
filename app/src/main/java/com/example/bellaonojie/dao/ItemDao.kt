package com.example.bellaonojie.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.bellaonojie.models.Item

@Dao
interface ItemDao {
    @Query("SELECT * FROM menu_table ORDER BY name ASC")
    fun getAllMenu(): LiveData<List<Item>>

    @Query("SELECT * FROM menu_table WHERE category = :category ORDER BY name ASC")
    fun getMenuForCategory(category: String): LiveData<List<Item>>

    @Query("SELECT * FROM menu_table WHERE name = :name ORDER BY name ASC")
    fun getItemsByName(name: String): LiveData<List<Item>>

    @Query("SELECT * FROM menu_table WHERE id = :id")
    suspend fun getItemById(id: Long): Item?

    @Query("SELECT * FROM menu_table WHERE name LIKE '%' || :search || '%'")
    fun getSearchedItems(search: String?): LiveData<List<Item>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(menuList: List<Item>)

    @Query("DELETE FROM menu_table")
    suspend fun deleteAll()
}