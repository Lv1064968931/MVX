package com.lvlin.mvvmdatabingdemo.data.api

import com.lvlin.mvvmdatabingdemo.data.model.User
import retrofit2.http.GET

/**
 * @author: lvlin
 * @email: lin2.lv@lvin.com
 * @date: 2022/7/12
 */
interface ApiService {

    @GET("users")
    suspend fun getUsers(): List<User>
}