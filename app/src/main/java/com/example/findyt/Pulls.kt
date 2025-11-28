package com.example.findyt

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class Pulls : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pulls)

        val backButton = findViewById<ImageView>(R.id.backButton)
        val helpButton = findViewById<ImageView>(R.id.helpButton)

        backButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        helpButton.setOnClickListener {
            val intent = Intent(this, Duda::class.java)
            startActivity(intent)
        }
    }
}