package com.example.findyt

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Hug : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hug)

        val gridMenuButton = findViewById<ImageView>(R.id.gridMenuButton)
        val helpButton = findViewById<ImageView>(R.id.helpButton)
        val chatButton = findViewById<Button>(R.id.chatButton)

        gridMenuButton.setOnClickListener {
            val popupMenu = PopupMenu(this, it)
            popupMenu.menuInflater.inflate(R.menu.grid_menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { item: MenuItem ->
                when (item.itemId) {
                    R.id.action_profile -> {
                        val intent = Intent(this, Perfil::class.java)
                        startActivity(intent)
                        true
                    }
                    R.id.action_logout -> {
                        val intent = Intent(this, Principal::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                        true
                    }
                    else -> false
                }
            }
            popupMenu.show()
        }

        helpButton.setOnClickListener {
            val intent = Intent(this, Duda::class.java)
            startActivity(intent)
        }

        chatButton.setOnClickListener {
            val intent = Intent(this, Chat::class.java)
            startActivity(intent)
        }
    }
}