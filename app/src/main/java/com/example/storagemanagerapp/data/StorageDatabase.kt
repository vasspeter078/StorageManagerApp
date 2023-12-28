package com.example.storagemanagerapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [StorageItem::class], version = 1)
abstract class StorageDatabase : RoomDatabase() {
    abstract fun storageItemDao(): StorageItemDao

    companion object {
        fun getDatabase(applicationContext: Context): StorageDatabase {
            return Room.databaseBuilder(
                applicationContext,
                StorageDatabase::class.java,
                "storage"
            ).build();
        }
    }
}