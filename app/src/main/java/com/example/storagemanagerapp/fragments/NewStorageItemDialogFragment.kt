package com.example.storagemanagerapp.fragments

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.storagemanagerapp.R
import com.example.storagemanagerapp.data.StorageItem
import com.example.storagemanagerapp.databinding.DialogNewStorageItemBinding

class NewStorageItemDialogFragment : DialogFragment() {
    interface NewStorageItemDialogListener {
        fun onStorageItemCreated(newItem: StorageItem)
    }

    private lateinit var listener: NewStorageItemDialogListener

    private lateinit var binding: DialogNewStorageItemBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? NewStorageItemDialogListener
            ?: throw RuntimeException("Activity must implement the NewStorageItemDialogListener interface!")
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogNewStorageItemBinding.inflate(LayoutInflater.from(context))

        return AlertDialog.Builder(requireContext())
            .setTitle(R.string.new_storage_item)
            .setView(binding.root)
            .setPositiveButton(R.string.button_ok) { dialogInterface, i ->
                if (isValid()) {
                    listener.onStorageItemCreated(getStorageItem())
                }
            }
            .setNegativeButton(R.string.button_cancel, null)
            .create()
    }

    companion object {
        const val TAG = "NewStorageItemDialogFragment"
    }
    private fun isValid() = binding.etName.text.isNotEmpty()

    private fun getStorageItem() = StorageItem(
        name = binding.etName.text.toString(),
        description = binding.etDescription.text.toString(),
        amount = binding.etAmount.text.toString().toIntOrNull() ?: 0,
        size = binding.etSize.text.toString().toIntOrNull() ?: 0
    )
}