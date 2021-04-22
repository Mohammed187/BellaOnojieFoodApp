package com.example.bellaonojie

import android.app.Application
import com.example.bellaonojie.repository.CartRepository
import com.example.bellaonojie.database.CartDatabase
import com.example.bellaonojie.database.MenuDatabase
import com.example.bellaonojie.database.UserDatabase
import com.example.bellaonojie.repository.MenuRepository
import com.example.bellaonojie.repository.UsersRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class BellaApplication : Application() {

    private val applicationScope = CoroutineScope(SupervisorJob())

    private val cartDatabase by lazy { CartDatabase.getDatabase(this, applicationScope) }

    val cartRepository by lazy { CartRepository(cartDatabase.cartDao()) }

    private val menuDatabase by lazy {
        MenuDatabase.getDatabase(this, applicationScope)
    }

    val menuRepository by lazy {
        MenuRepository(menuDatabase.itemDao())
    }

    private val userDatabase by lazy { UserDatabase.getDatabase(this, applicationScope) }

    val usersRepository by lazy { UsersRepository(userDatabase.userDao()) }
}