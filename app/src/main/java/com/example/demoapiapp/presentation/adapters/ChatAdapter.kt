package com.example.demoapiapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapiapp.databinding.ItemMessageBinding
import com.example.demoapiapp.presentation.activities.MessageItem

class ChatAdapter : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    inner class ChatViewHolder (val binding : ItemMessageBinding) : RecyclerView.ViewHolder(binding.root)

    var messages = listOf<MessageItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        return ChatViewHolder(ItemMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val messageItem = messages[position]
        with(holder.binding) {
            textMessage.text = messageItem.message
        }
    }

    fun updateAdapter (newMessages : List<MessageItem>) {
        messages = newMessages
        notifyDataSetChanged()
    }
}