package com.example.bellaonojie.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.bellaonojie.dao.ItemDao
import com.example.bellaonojie.data.menuItemsList
import com.example.bellaonojie.models.Item
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class MenuDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao

    private class MenuDatabaseCallback(private val scope: CoroutineScope) :
        RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.itemDao())
                }
            }
        }

        suspend fun populateDatabase(itemDao: ItemDao) {
            itemDao.deleteAll()
            itemDao.insert(menuItemsList())
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: MenuDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): MenuDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MenuDatabase::class.java,
                    "menu_database"
                )
                    .addCallback(MenuDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}