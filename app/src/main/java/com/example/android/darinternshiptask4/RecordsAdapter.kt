package com.example.android.darinternshiptask4

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.darinternshiptask4.databinding.UserRecordItemBinding

class RecordsAdapter(private val userRecords: List<User>) : RecyclerView.Adapter<RecordsAdapter.UserViewHolder>() {

    inner class UserViewHolder(private val binding: UserRecordItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.run {
                tvUserName.text = user.name
                tvWinCount.text = user.winCount.toString()
                tvLosesCount.text = user.loseCount.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UserRecordItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(userRecords[position])
    }

    override fun getItemCount() = userRecords.size
}