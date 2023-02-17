package com.example.newsapp.news

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.adapters.NewsAdapter
import com.example.newsapp.adapters.OnNewsClicked
import com.example.newsapp.databinding.FragmentNewsBinding
import com.example.newsapp.models.News

class NewsFragment : Fragment(),OnNewsClicked {

    private lateinit var adapter: NewsAdapter
    private lateinit var binding: FragmentNewsBinding
    private var newsList = mutableListOf<News>()

    private var timer: CountDownTimer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(layoutInflater,container,false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = NewsAdapter(this)
        binding.rvNews.layoutManager = LinearLayoutManager(context)
        binding.rvNews.adapter = adapter

        newsList.add(NewsRepository().getNew())
        adapter.setItems(newsList)

        setTimer()
    }

    private fun setTimer(){
        timer?.cancel()

        timer = object : CountDownTimer(3000,1){
            override fun onTick(p0: Long) {
            }
            override fun onFinish() {
                newsList.add(NewsRepository().getNew())
                adapter.setItems(newsList)
                setTimer()
            }
        }
        timer?.start()

    }

    override fun onItemClick(title: String, body: String) {
        val action = NewsFragmentDirections.actionNewsFragmentToNewsDetailsFragment(title,body)
        NavHostFragment.findNavController(this).navigate(action)
    }
}