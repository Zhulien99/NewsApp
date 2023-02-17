package com.example.newsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.database.RegisterEntity
import com.example.newsapp.databinding.UserItemBinding

class UserDetailsAdapter(private val itemListener: OnUserSelectListener)
    :RecyclerView.Adapter<UserDetailsViewHolder>(){

    var usersList = arrayListOf<RegisterEntity>()
    private lateinit var binding: UserItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserDetailsViewHolder {
        binding = UserItemBinding.inflate(LayoutInflater.from(parent.context))
        return UserDetailsViewHolder(binding.root,binding,itemListener)
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    override fun onBindViewHolder(holder: UserDetailsViewHolder, position: Int) {
        holder.bind(usersList,position)
    }

    fun setItems(user: List<RegisterEntity>){
        usersList.clear()
        usersList.addAll(user)
        notifyDataSetChanged()
    }
}

interface OnUserSelectListener {
    fun onUserSelected(userName: String,userId: Int)
}