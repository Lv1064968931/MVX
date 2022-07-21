package com.lvlin.mvidemo.ui.viewstate

import com.lvlin.mvidemo.data.model.User

/**
 * @author: lvlin
 * @email: lin2.lv@lvlin.com
 * @date: 2022/7/12
 */
sealed class MainState {

    object Idle : MainState()
    object Loading : MainState()
    data class Users(val user: List<User>) : MainState()
    data class Error(val error: String) : MainState()
}
