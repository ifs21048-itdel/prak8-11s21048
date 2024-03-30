package com.ifs21048.duplicateapp

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.ifs21048.duplicateapp.databinding.FragmentChatsFragmentsBinding

class ChatsFragments : Fragment() {
    private lateinit var binding: FragmentChatsFragmentsBinding
    private lateinit var floating_btn: FloatingActionButton
    private val dataChat: ArrayList<Chat> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatsFragmentsBinding.inflate(inflater, container, false)
        val view = binding.root

        floating_btn = view.findViewById<FloatingActionButton>(R.id.floating_btn)
        floating_btn.setOnClickListener {
            Toast.makeText(context, "Kamu memilih menu Buat Pesan Baru", Toast.LENGTH_SHORT).show()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val message = it.getString(EXTRA_MESSAGE)
            binding.tvFragmentChatMessage.text = message
        }

//        val listEmailAdapter = ListChatAdapter(dataChat)
//        listEmailAdapter.setOnItemClickCallback(object : ListChatAdapter.OnItemClickCallback {
//            override fun onItemClicked(data: Chat) {
//                showToast("Anda memilih salah satu chat")
//            }
//        })


        dataChat.addAll(getListEmail())
        showRecyclerList()
        binding.rvFilm.setHasFixedSize(false)
    }

//    private fun showToast(message: String) {
//        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
//    }

    companion object {
        const val EXTRA_MESSAGE = "extra_message"
    }

    @SuppressLint("Recycle")
    private fun getListEmail(): ArrayList<Chat> {
        val dataContact =
            resources.getStringArray(R.array.contact_name)
        val dataIcon =
            resources.obtainTypedArray(R.array.contact_profile)
        val dataChat =
            resources.getStringArray(R.array.contact_chat)

        val listChat = ArrayList<Chat>()
        for (i in dataContact.indices) {
            val chat = Chat(
                dataContact[i],
                dataIcon.getResourceId(i, -1),
                dataChat[i])
            listChat.add(chat)
        }
        return listChat
    }

    private fun showRecyclerList() {
        val layoutManager = if (requireActivity().resources.configuration.orientation ==
            Configuration.ORIENTATION_LANDSCAPE) {
            GridLayoutManager(requireContext(), 2)
        } else {
            LinearLayoutManager(requireContext())
        }
        binding.rvFilm.layoutManager = layoutManager
        val listEmailAdapter = ListChatAdapter(dataChat)
        binding.rvFilm.adapter = listEmailAdapter
    }


}
