package com.lvlin.mvvmdatabingdemo.data.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

/**
 * @author: lvlin
 * @email: lin2.lv@lvin.com
 * @date: 2022/7/12
 */
data class User(
    val id: Int = 0,
    val name: String = "",
    val email: String = "",
    val avatar: String = ""
)