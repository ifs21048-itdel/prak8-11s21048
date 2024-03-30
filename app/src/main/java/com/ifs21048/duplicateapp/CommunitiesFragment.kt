package com.ifs21048.duplicateapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ifs21048.duplicateapp.databinding.FragmentCommunitiesBinding

class CommunitiesFragment : Fragment() {
    private lateinit var binding: FragmentCommunitiesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCommunitiesBinding
            .inflate(inflater, container, false)
        return binding.root
    }
}
