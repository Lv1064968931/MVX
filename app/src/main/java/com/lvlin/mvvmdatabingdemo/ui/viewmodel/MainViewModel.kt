package com.lvlin.mvvmdatabingdemo.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lvlin.mvvmdatabingdemo.data.model.User
import com.lvlin.mvidemo.data.repository.MainRepository
import kotlinx.coroutines.launch

/**
 * @author: lvlin
 * @email: lin2.lv@lvin.com
 * @date: 2022/7/20
 */
class MainViewModel() : ViewModel() {

    companion object{
        const val TAG = "MainViewModel"
    }


    private val users = MutableLiveData<List<User>>()

    val _users : LiveData<List<User>>
        get() = users

    private val mainRepository by lazy { MainRepository() }


    fun fetchUsers(){
        viewModelScope.launch {
            users.value = mainRepository.getUsers()
            Log.d(TAG, "users == " + users.value + mainRepository.getUsers())
        }
    }

}