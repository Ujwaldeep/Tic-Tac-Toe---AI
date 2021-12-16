package com.example.customer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.customer.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.noAccountTv.setOnClickListener{
            val intent = Intent(this,RegistrationActivity::class.java)
            startActivity(intent)
        }
        binding.forgotTv.setOnClickListener {
            val intent = Intent(this,ForgetActivity::class.java)
            startActivity(intent)
        }
    }
}