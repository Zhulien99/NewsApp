package com.example.newsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.databinding.NewsItemBinding
import com.example.newsapp.models.News

class NewsAdapter(private val itemListener: OnNewsClicked)
    : RecyclerView.Adapter<NewsViewHolder>() {

    var newsList = arrayListOf<News>()
    private lateinit var binding: NewsItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        binding = NewsItemBinding.inflate(LayoutInflater.from(parent.context))
        return NewsViewHolder(binding.root,binding,itemListener)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(newsList, position)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    fun setItems(news: List<News>){
        newsList.clear()
        newsList.addAll(news)
        notifyDataSetChanged()
    }
}

interface OnNewsClicked {
    fun onItemClick(title: String, body: String)
}
