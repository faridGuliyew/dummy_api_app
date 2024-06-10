package com.example.demoapiapp.presentation.activities

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.demoapiapp.R
import com.example.demoapiapp.databinding.ActivityChatBinding
import com.example.demoapiapp.presentation.adapters.ChatAdapter
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ChatActivity : AppCompatActivity() {
    lateinit var binding: ActivityChatBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val firestore = Firebase.firestore

        val adapter = ChatAdapter()
        binding.messagesRv.adapter = adapter

        binding.buttonSend.setOnClickListener {
            //get message
            val messageToSend = binding.editTextMessage.text.toString()
            binding.editTextMessage.setText("")
            //send message
            firestore
                .collection("messages")
                .document()
                .set(MessageItem(messageToSend))
                .addOnFailureListener {
                    Toast.makeText(applicationContext, "Message failed to send", Toast.LENGTH_SHORT).show()
                }
        }

        firestore.collection("messages").addSnapshotListener { value, error ->
            if (error != null) {
                Toast.makeText(applicationContext, "Message failed to get", Toast.LENGTH_SHORT).show()
            } else {
                val messages = value!!.toObjects(MessageItem::class.java)
                messages.sortBy { it.messageDate }
                adapter.updateAdapter(messages)
            }
        }

            firestore.collection("asf").document().set("asf").addOnCompleteListener {
                Toast.makeText(applicationContext, "Result: ${it.exception?.message}", Toast.LENGTH_SHORT).show()
            }

    }
}

data class MessageItem (
    val message: String = "",
    val messageDate : String = System.currentTimeMillis().toString()
)