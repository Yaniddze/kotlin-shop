package com.example.kotlin_shop.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.kotlin_shop.data.dao.CartItemDao
import com.example.kotlin_shop.data.dao.FavoriteProductsDao
import com.example.kotlin_shop.data.dao.ViewedProductsDao
import com.example.kotlin_shop.data.entities.CartItemDB
import com.example.kotlin_shop.data.entities.FavoriteProductDB
import com.example.kotlin_shop.data.entities.ViewedProductDB

@Database(
    entities = [CartItemDB::class, ViewedProductDB::class, FavoriteProductDB::class],
    version = 5,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cartItemDao(): CartItemDao

    abstract fun viewedProductsDao(): ViewedProductsDao

    abstract fun favoriteDao(): FavoriteProductsDao

    companion object {

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "CREATE TABLE IF NOT EXISTS viewed_products(" +
                            "id INTEGER NOT NULL, " +
                            "productId INTEGER NOT NULL, " +
                            "title VARCHAR NOT NULL, " +
                            "imageUrl VARCHAR NOT NULL, " +
                            "PRIMARY KEY(id))"
                )
            }

        }

        private val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "CREATE TABLE IF NOT EXISTS favorites(" +
                            "id INTEGER NOT NULL, " +
                            "productId INTEGER NOT NULL," +
                            "PRIMARY KEY(id))"
                )
            }

        }

        private val MIGRATION_3_4 = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE favorites ADD COLUMN image TEXT NOT NULL DEFAULT '0'")
                database.execSQL("ALTER TABLE favorites ADD COLUMN title TEXT NOT NULL DEFAULT '0'")
            }
        }

        private val MIGRATION_4_5 = object : Migration(4, 5) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE cart_item ADD COLUMN count Int NOT NULL DEFAULT 1")
            }

        }

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE != null)
                return INSTANCE!!

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .addMigrations(
                        MIGRATION_1_2,
                        MIGRATION_2_3,
                        MIGRATION_3_4,
                        MIGRATION_4_5
                    )
                    .build()

                INSTANCE = instance

                return instance
            }
        }
    }
}