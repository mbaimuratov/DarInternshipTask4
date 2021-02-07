package com.example.android.darinternshiptask4.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.android.darinternshiptask4.R
import com.example.android.darinternshiptask4.RecordsAdapter
import com.example.android.darinternshiptask4.UsersStorage
import com.example.android.darinternshiptask4.databinding.FragmentRecordsBinding

class RecordsFragment : Fragment(R.layout.fragment_records) {
    private lateinit var adapter: RecordsAdapter
    private lateinit var usersStorage: UsersStorage
    private lateinit var binding: FragmentRecordsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        usersStorage = UsersStorage.getInstance()!!
        adapter = RecordsAdapter(usersStorage.getUsers())

        binding = FragmentRecordsBinding.bind(view)
        binding.rvRecords.adapter = adapter

        binding.actionToMainPage.setOnClickListener {
            findNavController().navigate(R.id.action_recordsFragment_to_titleFragment)
        }
    }
}