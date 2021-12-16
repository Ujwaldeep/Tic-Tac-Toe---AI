package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var p = false;
        power.setOnClickListener{
            if(p==false) {
                p=true;
                Toast.makeText(this, "Machine is OFF", Toast.LENGTH_LONG).show()
                preView.text = "Switch is OFF"
            }else{
                p=false;
                Toast.makeText(this, "Machine is ON", Toast.LENGTH_LONG).show()
                preView.text = "Switch is ON"
            }
        }
    }
}