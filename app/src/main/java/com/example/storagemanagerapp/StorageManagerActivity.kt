package com.example.storagemanagerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.storagemanagerapp.adapter.StorageAdapter
import com.example.storagemanagerapp.data.StorageDatabase
import com.example.storagemanagerapp.data.StorageItem
import com.example.storagemanagerapp.databinding.ActivityStorageManagerBinding
import com.example.storagemanagerapp.fragments.NewStorageItemDialogFragment
import kotlin.concurrent.thread

class StorageManagerActivity : AppCompatActivity(), StorageAdapter.StorageItemClickListener, NewStorageItemDialogFragment.NewStorageItemDialogListener {
    private lateinit var binding: ActivityStorageManagerBinding

    private lateinit var database: StorageDatabase
    private lateinit var adapter: StorageAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStorageManagerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        database = StorageDatabase.getDatabase(applicationContext)

        binding.fab.setOnClickListener {
            NewStorageItemDialogFragment().show(
                supportFragmentManager,
                NewStorageItemDialogFragment.TAG
            )
        }
        initRecyclerView()
    }
    private fun initRecyclerView() {
        adapter = StorageAdapter(this)
        binding.rvMain.layoutManager = LinearLayoutManager(this)
        binding.rvMain.adapter = adapter
        loadItemsInBackground()
    }

    private fun loadItemsInBackground() {
        thread {
            val items = database.storageItemDao().getAll()
            runOnUiThread {
                adapter.update(items)
            }
        }
    }
    override fun onItemChanged(item: StorageItem) {
        thread {
            database.storageItemDao().update(item)
            Log.d("StorageManagerActivity", "StorageItem update was successful")
        }
    }
    override fun onStorageItemCreated(newItem: StorageItem) {
        thread {
            val insertId = database.storageItemDao().insert(newItem)
            newItem.id = insertId
            runOnUiThread {
                adapter.addItem(newItem)
            }
        }
    }
    override fun onItemDeleted(item: StorageItem) {
        thread {
            val deleteId = database.storageItemDao().deleteItem(item)
            runOnUiThread {
                adapter.removeItem(item)
            }
        }
    }
}