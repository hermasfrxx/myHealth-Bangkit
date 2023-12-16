package com.dicoding.myhealth.api.daily

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Dailycalori::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    companion object{
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app database"
                    ).fallbackToDestructiveMigration().build()
                }
            }
            return INSTANCE as AppDatabase
        }
    }
    abstract fun dailyDao():DailyDAO
}