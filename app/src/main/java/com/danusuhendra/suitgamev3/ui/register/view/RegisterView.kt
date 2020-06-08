package com.danusuhendra.suitgamev3.ui.register.view

import com.danusuhendra.suitgamev3.data.network.model.users.response.ResponseUsers

interface RegisterView {
    fun showResult(data : ResponseUsers)
    fun onError(it: String?)
    fun hideLoading()
    fun showLoading()
}