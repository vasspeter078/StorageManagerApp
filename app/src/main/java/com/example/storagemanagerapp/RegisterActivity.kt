package com.example.storagemanagerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.storagemanagerapp.databinding.ActivityLoginBinding
import com.example.storagemanagerapp.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnRegister.setOnClickListener {
            if (binding.etEmailAddress.text.toString().isEmpty()) {
                binding.etEmailAddress.requestFocus()
                binding.etEmailAddress.error = "Please enter your email address"
            } else if (binding.etPassword.text.toString().isEmpty()) {
                binding.etPassword.requestFocus()
                binding.etPassword.error = "Please enter your password"
            } else if (binding.etUsername.text.toString().isEmpty()) {
                binding.etUsername.requestFocus()
                binding.etUsername.error = "Please enter your username"
            }
            else {
                var sharedPreferences = getSharedPreferences("users", MODE_PRIVATE)
                var editor = sharedPreferences.edit()
                editor.putInt(binding.etEmailAddress.text.toString(), binding.etPassword.text.toString().toInt())
                editor.commit()
                startActivity(Intent(this, LoginActivity::class.java))
            }
        }
    }
}