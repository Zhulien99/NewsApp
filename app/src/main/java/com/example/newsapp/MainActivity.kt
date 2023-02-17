package com.example.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.example.newsapp.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val navController by lazy { Navigation.findNavController(this, R.id.myNavHostFragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        navController.addOnDestinationChangedListener { _, destination, _ ->
            updateBottomNavVisibility(destination)
        }

        bottomBar.setupWithNavController(navController)
    }

    private fun updateBottomNavVisibility(destination: NavDestination) {
        when (destination.id) {
            R.id.loginFragment,
            R.id.registerFragment,
            R.id.userEditFragment -> {
                hideBottomNav()
            }
            else -> {
                showBottomNav()
            }
        }
    }

    private fun showBottomNav() {
        bottomBar.visibility = View.VISIBLE
    }

    private fun hideBottomNav() {
        bottomBar.visibility = View.GONE
    }
}