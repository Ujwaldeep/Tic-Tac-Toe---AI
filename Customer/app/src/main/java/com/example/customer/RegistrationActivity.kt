package com.example.customer

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.customer.databinding.ActivityLoginBinding
import com.example.customer.databinding.ActivityRegistrationBinding

class RegistrationActivity : AppCompatActivity() {
    private lateinit var binding:ActivityRegistrationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backBtn.setOnClickListener {
           onBackPressed()
        }
    }
}