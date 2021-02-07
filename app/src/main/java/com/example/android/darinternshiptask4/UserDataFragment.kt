package com.example.android.darinternshiptask4

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.android.darinternshiptask4.databinding.FragmentUserDataBinding
import com.google.android.material.snackbar.Snackbar

lateinit var binding: FragmentUserDataBinding

private lateinit var firstUserName: String
private lateinit var secondUserName: String

class UserDataFragment : Fragment(R.layout.fragment_user_data) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUserDataBinding.bind(view)

        binding.actionStartGame.setOnClickListener {
            firstUserName = binding.user1Et.text.toString()
            secondUserName = binding.user2Et.text.toString()
            if (firstUserName.isEmpty() || secondUserName.isEmpty()) {
                val imm: InputMethodManager =
                    requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(requireView().windowToken, 0)
                Snackbar.make(
                    requireView(),
                    "Fill both user names",
                    Snackbar.LENGTH_SHORT
                )
                    .show()
            } else if (firstUserName == secondUserName) {
                val imm: InputMethodManager =
                    requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(requireView().windowToken, 0)
                Snackbar.make(
                    requireView(),
                    "Users cannot have the same name",
                    Snackbar.LENGTH_SHORT
                )
                    .show()
            } else {
                val action = UserDataFragmentDirections.actionUserDataFragmentToGameFragment(
                    firstUserName,
                    secondUserName
                )

                findNavController().navigate(action)
            }
        }
    }


}