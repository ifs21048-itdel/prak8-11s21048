package com.ifs21048.duplicateapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifs21048.duplicateapp.databinding.ItemRowChatBinding

class ListChatAdapter(private val listChat: ArrayList<Chat>) :
    RecyclerView.Adapter<ListChatAdapter.ListViewHolder>() {

//    private lateinit var onItemClickCallback: OnItemClickCallback

//    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
//        this.onItemClickCallback = onItemClickCallback
//    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowChatBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val chat = listChat[position]
        holder.binding.ivProfileItem.setImageResource(chat.contactProfile)
        holder.binding.tvItemContact.text = chat.contactName
        holder.binding.tvItemChat.text = chat.contactChat

//        holder.itemView.setOnClickListener {
//            onItemClickCallback.onItemClicked(listChat[holder.adapterPosition])
//        }
    }

    override fun getItemCount(): Int = listChat.size

    class ListViewHolder(var binding: ItemRowChatBinding) : RecyclerView.ViewHolder(binding.root)

//    interface OnItemClickCallback {
//        fun onItemClicked(data: Chat)
//    }
}