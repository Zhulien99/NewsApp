package com.example.newsapp.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newsapp.databinding.FragmentNewsDetailsBinding

class NewsDetailsFragment : Fragment() {

    private lateinit var binding: FragmentNewsDetailsBinding
    private var newsTitle = ""
    private var newsBody = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsDetailsBinding.inflate(layoutInflater,container,false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsTitle = NewsDetailsFragmentArgs.fromBundle(requireArguments()).newsTitle
        newsBody = NewsDetailsFragmentArgs.fromBundle(requireArguments()).newsBody

        binding.tvNewsTitle.text = newsTitle
        binding.tvNewsBody.text = newsBody
    }
}