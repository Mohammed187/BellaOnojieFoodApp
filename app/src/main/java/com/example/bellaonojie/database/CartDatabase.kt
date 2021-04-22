package com.example.bellaonojie.database

import android.content.Context
import androidx.databinding.adapters.Converters
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.bellaonojie.dao.CartDao
import com.example.bellaonojie.data.cartItemsList
import com.example.bellaonojie.data.menuItemsList
import com.example.bellaonojie.models.Cart
import com.example.bellaonojie.models.Item
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Cart::class, Item::class], version = 1, exportSchema = false)
abstract class CartDatabase : RoomDatabase() {

    abstract fun cartDao(): CartDao

    private class CartDatabaseCallback(private val scope: CoroutineScope) :
            RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.cartDao())
                }
            }
        }

        suspend fun populateDatabase(cartDao: CartDao) {
            cartDao.deleteAll()
            cartDao.insertCartItems(cartItemsList())
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: CartDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): CartDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        CartDatabase::class.java,
                        "cart_database"
                )
                        .addCallback(CartDatabaseCallback(scope))
                        .build()
                INSTANCE = instance
                instance
            }
        }
    }
}