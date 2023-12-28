package com.example.storagemanagerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.content.Intent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.storagemanagerapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLogin.setOnClickListener {
            if(binding.etEmailAddress.text.toString().isEmpty()) {
                binding.etEmailAddress.requestFocus()
                binding.etEmailAddress.error = "Please enter your email address"
            }
            else if(binding.etPassword.text.toString().isEmpty()) {
                binding.etPassword.requestFocus()
                binding.etPassword.error = "Please enter your password"
            }
            else {
                var sharedPreferences = getSharedPreferences("users", MODE_PRIVATE)
                if (sharedPreferences.contains(binding.etEmailAddress.text.toString())) {
                    val pw = sharedPreferences.getInt(binding.etEmailAddress.text.toString(), -1)
                    if (pw == binding.etPassword.text.toString().toInt()) {
                        startActivity(Intent(this, StorageManagerActivity::class.java))
                    } else {
                        binding.etPassword.requestFocus()
                        binding.etPassword.error = "Wrong password"
                    }
                } else {
                    binding.etEmailAddress.requestFocus()
                    binding.etEmailAddress.error = "This email address does not have a user"
                }


            }
        }
        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}