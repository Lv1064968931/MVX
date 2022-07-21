package com.lvlin.mvidemo.data.api

import com.lvlin.mvidemo.data.model.User
import retrofit2.http.GET

/**
 * @author: lvlin
 * @email: lin2.lv@lvlin.com
 * @date: 2022/7/12
 */
interface ApiService {

    @GET("users")
    suspend fun getUsers(): List<User>
}