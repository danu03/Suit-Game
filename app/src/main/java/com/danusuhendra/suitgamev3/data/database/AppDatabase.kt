package com.danusuhendra.suitgamev3.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.danusuhendra.suitgamev3.data.database.model.SaveBattle

@Database(entities = [SaveBattle::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun saveDataDao() : SaveDataDao

//    companion object{
//        @Volatile
//        private var INSTANCE : AppDatabase? = null
//        fun getDatabase(context: Context) : AppDatabase {
//            val tempInstance = INSTANCE
//            if (tempInstance != null){
//                return tempInstance
//            }
//
//            synchronized(this){
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    AppDatabase::class.java,
//                    "saveDatabase"
//                ).build()
//                INSTANCE = instance
//                return instance
//            }
//        }
}