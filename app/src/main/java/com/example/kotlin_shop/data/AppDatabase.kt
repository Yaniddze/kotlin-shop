package com.example.kotlin_shop.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.kotlin_shop.data.dao.CartItemDao
import com.example.kotlin_shop.data.dao.ViewedProductsDao
import com.example.kotlin_shop.data.entities.CartItemDB
import com.example.kotlin_shop.data.entities.ViewedProductDB

@Database(
    entities = [CartItemDB::class, ViewedProductDB::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cartItemDao(): CartItemDao

    abstract fun viewedProductsDao(): ViewedProductsDao



    companion object{

        private val MIGRAGION_1_2 = object: Migration(1, 2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "CREATE TABLE IF NOT EXISTS viewed_products(" +
                            "id INTEGER NOT NULL, " +
                            "productId INTEGER NOT NULL, " +
                            "title VARCHAR NOT NULL, " +
                            "imageUrl VARCHAR NOT NULL, " +
                            "PRIMARY KEY(id))")
            }

        }

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            if(INSTANCE != null)
                return INSTANCE!!

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                .addMigrations(MIGRAGION_1_2)
                .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}