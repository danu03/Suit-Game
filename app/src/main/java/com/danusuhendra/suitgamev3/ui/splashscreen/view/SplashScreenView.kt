package com.danusuhendra.suitgamev3.ui.splashscreen.view

import com.danusuhendra.suitgamev3.data.network.model.users.response.ResponseAuthMe

interface SplashScreenView {
    fun isLogin(status : Boolean)
    fun onSuccess(data : ResponseAuthMe)
    fun onError(it : String?)
}