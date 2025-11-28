package com.example.findyt

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
class Chat : AppCompatActivity() {

    private val messageList = mutableListOf<String>()
    private lateinit var chatAdapter: ChatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val chatRecyclerView = findViewById<RecyclerView>(R.id.chatRecyclerView)
        val messageEditText = findViewById<EditText>(R.id.messageEditText)
        val sendButton = findViewById<ImageView>(R.id.sendButton)
        val backButton = findViewById<ImageView>(R.id.backButton)
        val helpButton = findViewById<ImageView>(R.id.helpButton)


        chatAdapter = ChatAdapter(messageList)

        chatRecyclerView.layoutManager = LinearLayoutManager(this)

        chatRecyclerView.adapter = chatAdapter

        sendButton.setOnClickListener {
            val messageText = messageEditText.text.toString()
            if (messageText.isNotEmpty()) {
                messageList.add(messageText)

                chatAdapter.notifyItemInserted(messageList.size - 1)

                chatRecyclerView.scrollToPosition(messageList.size - 1)

                messageEditText.text.clear()
            }
        }
        backButton.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)

        }
        helpButton.setOnClickListener {
            val intent = Intent(this, Duda::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

    }
}
