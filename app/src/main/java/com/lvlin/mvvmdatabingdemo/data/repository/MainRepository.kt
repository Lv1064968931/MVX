package com.lvlin.mvidemo.data.repository

import com.lvlin.mvvmdatabingdemo.data.api.RetrofitBuilder

/**
 * @author: lvlin
 * @email: lin2.lv@lvin.com
 * @date: 2022/7/12
 */
class MainRepository {

    suspend fun getUsers() = RetrofitBuilder.apiService.getUsers()
}