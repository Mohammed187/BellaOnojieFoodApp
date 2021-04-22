package com.example.bellaonojie.repository

import androidx.lifecycle.LiveData
import com.example.bellaonojie.dao.UserDao
import com.example.bellaonojie.models.User

class UsersRepository(private val userDao: UserDao) {

    suspend fun getUserDetails(id: Long): User? {
        return userDao.getUserDetails(id)
    }

    fun getUserDataToLogin(email: String, password: String): LiveData<User> {
        return userDao.getUserData(email, password)
    }

    suspend fun insert(user: User) {
        userDao.insertUserExample(user)
    }
}