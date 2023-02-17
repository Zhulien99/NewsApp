package com.example.newsapp.userDetails.userEdit

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.database.RegisterRepository
import com.example.newsapp.userDetails.UserDetailsViewModel
import java.lang.IllegalArgumentException

class UserEditViewModelFactory( private  val repository: RegisterRepository,
private val application: Application
): ViewModelProvider.Factory {
    @Suppress("Unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserEditViewModel::class.java)) {
            return UserEditViewModel(repository, application) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}