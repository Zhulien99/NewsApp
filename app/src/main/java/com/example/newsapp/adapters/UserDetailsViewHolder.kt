package com.example.newsapp.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.database.RegisterEntity
import com.example.newsapp.databinding.UserItemBinding

class UserDetailsViewHolder(
    itemView: View,
    private val binding : UserItemBinding,
    private val itemListener: OnUserSelectListener): RecyclerView.ViewHolder(itemView){

    fun bind(userList : List<RegisterEntity>,position: Int){
        binding.firstNameText.text = userList[position].firstName
        binding.secondNameText.text = userList[position].lastName
        binding.userNick.text = userList[position].userName
        binding.userPassTxt.text = userList[position].passwrd

        binding.cardView.setOnClickListener {
            itemListener.onUserSelected(userList[position].userName,userList[position].userId)
        }
    }
}