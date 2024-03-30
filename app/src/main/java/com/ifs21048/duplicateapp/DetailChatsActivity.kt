package com.ifs21048.duplicateapp

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ifs21048.duplicateapp.Chat
import com.ifs21048.duplicateapp.databinding.ActivityDetailChatsBinding

class DetailChatsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailChatsBinding
    private var chat: Chat? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailChatsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        chat = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_CHAT, Chat::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_CHAT)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (chat != null) {
            supportActionBar?.title = "Chat ${chat!!.contactName}"
            loadData(chat!!)
        } else {
            finish()
        }
    }

    private fun loadData(chat: Chat) {
        binding.ivProfileItem.setImageResource(chat.contactProfile)
        binding.tvContacDetailName.text = chat.contactName
        binding.tvPreviewChat.text = chat.contactChat
    }

    companion object {
        const val EXTRA_CHAT = "extra_chat"
    }
}