package com.example.storagemanagerapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface StorageItemDao {
    @Query("SELECT * FROM storageitem")
    fun getAll(): List<StorageItem>

    @Insert
    fun insert(storageItems: StorageItem): Long

    @Update
    fun update(storageItem: StorageItem)

    @Delete
    fun deleteItem(storageItem: StorageItem)
}