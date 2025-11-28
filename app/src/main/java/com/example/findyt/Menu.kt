package com.example.findyt

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Menu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val profileButton = findViewById<TextView>(R.id.profileButton)
        val logoutButton = findViewById<TextView>(R.id.logoutButton)
        val profileImageView = findViewById<ImageView>(R.id.profileImageView)
        val pullsButton = findViewById<Button>(R.id.pullsButton)
        val chatButton = findViewById<Button>(R.id.chatButton)

        profileButton.setOnClickListener {
            val intent = Intent(this, Perfil::class.java)
            startActivity(intent)
        }

        logoutButton.setOnClickListener {
            val intent = Intent(this, Principal::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        profileImageView.setOnClickListener {
            val intent = Intent(this, Pushypull::class.java)
            startActivity(intent)
        }

        pullsButton.setOnClickListener {
            val intent = Intent(this, Pulls::class.java)
            startActivity(intent)
        }

        chatButton.setOnClickListener {
            val intent = Intent(this, Chat::class.java)
            startActivity(intent)
        }
    }
}
