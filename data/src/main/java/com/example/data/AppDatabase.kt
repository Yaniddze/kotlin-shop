package com.example.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.dao.ICartItemDao
import com.example.data.entities.CartItemDB

@Database(
    entities = [CartItemDB::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase(){
    abstract fun cartItemDao(): ICartItemDao


    companion object{
        var context: Context? = null

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(): AppDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null)
                return tempInstance

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context!!.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}