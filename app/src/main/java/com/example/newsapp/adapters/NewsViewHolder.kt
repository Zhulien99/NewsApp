package com.example.newsapp.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.database.RegisterEntity
import com.example.newsapp.databinding.NewsItemBinding
import com.example.newsapp.databinding.UserItemBinding
import com.example.newsapp.models.News

class NewsViewHolder(
    itemView: View,
    private val binding : NewsItemBinding,
    private val itemListener: OnNewsClicked): RecyclerView.ViewHolder(itemView) {

    fun bind(newsList : List<News>, position: Int){
        binding.tvTitle.text = newsList[position].title

        binding.tvTitle.setOnClickListener {
            itemListener.onItemClick(newsList[position].title,newsList[position].textBody)
        }
    }
}