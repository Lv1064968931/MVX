package com.lvlin.mvidemo.data.repository

import com.lvlin.mvidemo.data.api.RetrofitBuilder

/**
 * @author: lvlin
 * @email: lin2.lv@lvlin.com
 * @date: 2022/7/12
 */
object MainRepository {

    suspend fun getUsers() = RetrofitBuilder.apiService.getUsers()
}