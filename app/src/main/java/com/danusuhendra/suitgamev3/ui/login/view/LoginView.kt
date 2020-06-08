package com.danusuhendra.suitgamev3.ui.login.view

import com.danusuhendra.suitgamev3.data.network.model.users.response.ResponseUsers

interface LoginView{
    fun showResult(data : ResponseUsers)
    fun onError(it: String?)
    fun hideLoading()
    fun showLoading()
}