package com.example.newsapp.chat

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentChatBinding
import java.util.*

class ChatFragment : Fragment() {

    private lateinit var binding: FragmentChatBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatBinding.inflate(layoutInflater,container,false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etMessage.setOnClickListener {

        }

        binding.btnSend.setOnClickListener {
            val message = binding.etMessage.text.toString()

            if (message.isNotEmpty()){
                //tvChatMessage.text = chatViewModel.checkMessage(message)
                val response = checkMessage(message)
                if (response != "Invalid command" ){
                    binding.tvChatMessage.text = response
                }else{
                    binding.tvError.visibility = View.VISIBLE
                }
            }else{
                binding.tvError.visibility = View.GONE
            }
        }

    }

    fun checkMessage(message: String): String{
        when(message){
            "Good morning, what day it is?" -> return "Today is ${getWeekDay()}"
            "Hello, what time is it?" -> return "Now is ${getCurrTime()}"
            "Hello, what is the weather?" -> return "The weather is ${getCurrWeather()}"
            "Hello" -> return "The temperature is ${getCurrTemperature()} C"
            else -> return "Invalid command"
        }
    }

    private fun getWeekDay(): String{
        val calendar: Calendar = Calendar.getInstance()
        val day: Int = calendar.get(Calendar.DAY_OF_WEEK)

        when (day) {
            Calendar.SUNDAY -> return "sunday"
            Calendar.MONDAY -> return "monday"
            Calendar.TUESDAY -> return "tuesday"
            Calendar.THURSDAY -> return "thursday"
            Calendar.FRIDAY -> return "friday"
            Calendar.SATURDAY -> return "saturday"
            Calendar.WEDNESDAY -> return "wednesday"
            else -> return "unknown day"
        }
    }

    private fun getCurrTime(): String {
        val calendar: Calendar = Calendar.getInstance()
        val time = calendar.time
        Log.d("Time", time.toString())

        return time.toString()
    }

    private fun getCurrWeather(): String{
        val weatherType: List<String> = listOf("Cold", "Warm", "Wet", "Cloudy", "Rainy", "Sunny")
        val randomIndex = (0..5).random()

        return weatherType[randomIndex]
    }

    private fun getCurrTemperature(): String {
        val temp = (-15..30).random()

        return temp.toString()
    }

}