package com.lvlin.mvidemo.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lvlin.mvidemo.data.api.ApiHelper
import com.lvlin.mvidemo.data.repository.MainRepository
import com.lvlin.mvidemo.ui.viewmodel.MainViewModel

/**
 * @author: lvlin
 * @email: lin2.lv@lvlin.com
 * @date: 2022/7/12
 */
class ViewModelFactory(private val apiHelper: ApiHelper): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(MainRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}