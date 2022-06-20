package com.assessment.cartapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.assessment.cartapp.model.CartItem

@Database(entities = arrayOf(CartItem::class), version = 1, exportSchema = false)
@TypeConverters(ServiceConvertor::class, EmployeeConvertor::class)
public abstract class CartDatabase : RoomDatabase() {

    abstract fun wordDao(): CartItemDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: CartDatabase? = null

        fun getDatabase(context: Context): CartDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CartDatabase::class.java,
                    "cart_db"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}