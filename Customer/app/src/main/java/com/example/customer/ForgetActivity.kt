package com.example.customer

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.customer.databinding.ActivityForgetBinding

class ForgetActivity : AppCompatActivity() {

private lateinit var binding: ActivityForgetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

     binding = ActivityForgetBinding.inflate(layoutInflater)
     setContentView(binding.root)

        binding.backBtn.setOnClickListener {
            onBackPressed()
        }


    }


}