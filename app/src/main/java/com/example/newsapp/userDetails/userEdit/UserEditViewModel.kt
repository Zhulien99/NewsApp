package com.example.newsapp.userDetails.userEdit

import android.app.Application
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.*
import com.example.newsapp.database.RegisterRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class UserEditViewModel(private val repository: RegisterRepository, application: Application) :
    AndroidViewModel(application),Observable {

    @Bindable
    val inputUsername = MutableLiveData<String?>()

    @Bindable
    val inputPassword = MutableLiveData<String?>()

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    private val _navigateto = MutableLiveData<Boolean>()

    private val _errorToast = MutableLiveData<Boolean>()

    val navigateto: LiveData<Boolean>
        get() = _navigateto

    var userId = 0

    fun sumbitButton() {
        if (inputUsername.value == null || inputPassword.value == null) {
            _errorToast.value = true
        } else {
            uiScope.launch {
                    _errorToast.value = true
                    _navigateto.value = true
                    val email = inputUsername.value!!
                    val passwordNew = inputPassword.value!!
                    updateUserName(email,userId)
                    updatePassword(passwordNew,userId)
                    inputUsername.value = null
                    inputPassword.value = null
            }
        }
    }

    private fun updateUserName(userName: String,id: Int): Job = viewModelScope.launch {
        repository.updateUserName(userName,id)
    }
    private fun updatePassword(password: String,id: Int): Job = viewModelScope.launch {
        repository.updatePassword(password,id)
    }
    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

}