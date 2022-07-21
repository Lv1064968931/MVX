package com.lvlin.mvidemo.data.api

import com.lvlin.mvidemo.data.model.User

/**
 * @author: lvlin
 * @email: lin2.lv@lvlin.com
 * @date: 2022/7/12
 */
interface ApiHelper {

    suspend fun getUsers(): List<User>
}