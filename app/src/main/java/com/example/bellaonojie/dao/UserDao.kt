package com.example.bellaonojie.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bellaonojie.models.User

@Dao
interface UserDao {

    @Query("SELECT * FROM users_table WHERE id= :id")
    suspend fun getUserDetails(id: Long): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserExample(user: User)

    @Query("SELECT * FROM users_table")
    fun getAllUsers(): LiveData<List<User>>

    @Query("SELECT * FROM users_table WHERE email LIKE :email AND password LIKE:password")
    fun getUserData(email: String, password: String): LiveData<User>

    @Query("DELETE FROM users_table")
    suspend fun deleteAll()
}