package com.lvlin.mvidemo.data.repository

import com.lvlin.mvidemo.data.api.ApiHelper

/**
 * @author: lvlin
 * @email: lin2.lv@lvlin.com
 * @date: 2022/7/12
 */
class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getUsers() = apiHelper.getUsers()
}