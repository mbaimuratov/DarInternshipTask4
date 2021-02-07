package com.example.android.darinternshiptask4.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.android.darinternshiptask4.R
import com.example.android.darinternshiptask4.databinding.FragmentTitleBinding

class TitleFragment : Fragment(R.layout.fragment_title) {

    private lateinit var binding: FragmentTitleBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTitleBinding.bind(view)

        val navController = findNavController()

        binding.actionNewGame.setOnClickListener {
            val action = TitleFragmentDirections.actionTitleFragmentToUserDataFragment()
            navController.navigate(action)
        }

        binding.actionUserRecords.setOnClickListener {
            navController.navigate(R.id.action_titleFragment_to_recordsFragment)
        }
    }
}