package com.example.findyt

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.imageview.ShapeableImageView

class ListaChats : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_chats)

        val backButton = findViewById<ImageView>(R.id.backButton)

        val chatGuillermoView = findViewById<View>(R.id.chat_guillermo)
        val chatFerView = findViewById<View>(R.id.chat_fer)
        val chatEstebanView = findViewById<View>(R.id.chat_esteban)
        val chatJoseView = findViewById<View>(R.id.chat_jose)


        val guillermoProfileImage = chatGuillermoView.findViewById<ShapeableImageView>(R.id.chatListProfileImageView)
        val guillermoNameText = chatGuillermoView.findViewById<TextView>(R.id.chatListNameTextView)
        guillermoProfileImage.setImageResource(R.drawable.fotohombre)
        guillermoNameText.text = "Guillermo Ray"

        val ferProfileImage = chatFerView.findViewById<ShapeableImageView>(R.id.chatListProfileImageView)
        val ferNameText = chatFerView.findViewById<TextView>(R.id.chatListNameTextView)
        ferProfileImage.setImageResource(R.drawable.fotohombre)
        ferNameText.text = "Fer Espinoza"

        val estebanProfileImage = chatEstebanView.findViewById<ShapeableImageView>(R.id.chatListProfileImageView)
        val estebanNameText = chatEstebanView.findViewById<TextView>(R.id.chatListNameTextView)
        estebanProfileImage.setImageResource(R.drawable.fotohombre)
        estebanNameText.text = "Esteban Rosas"

        val joseProfileImage = chatJoseView.findViewById<ShapeableImageView>(R.id.chatListProfileImageView)
        val joseNameText = chatJoseView.findViewById<TextView>(R.id.chatListNameTextView)
        joseProfileImage.setImageResource(R.drawable.fotohombre)
        joseNameText.text = "Jose Rogelio"


        backButton.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }

        val clickListener = View.OnClickListener {
            val intent = Intent(this, Chat::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        chatGuillermoView.setOnClickListener(clickListener)
        chatFerView.setOnClickListener(clickListener)
        chatEstebanView.setOnClickListener(clickListener)
        chatJoseView.setOnClickListener(clickListener)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}









