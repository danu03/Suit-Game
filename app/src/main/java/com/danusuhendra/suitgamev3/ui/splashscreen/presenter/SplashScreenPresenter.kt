package com.danusuhendra.suitgamev3.ui.splashscreen.presenter

import com.danusuhendra.suitgamev3.repository.AuthRepository
import com.danusuhendra.suitgamev3.ui.splashscreen.view.SplashScreenView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SplashScreenPresenter(private val repository: AuthRepository) {
    private lateinit var view: SplashScreenView

    fun setView(view: SplashScreenView){
        this.view = view
    }

    fun userAuth(token : String) {
        GlobalScope.launch(Dispatchers.Main) {
            repository.userAuth(token, {
                view.isLogin(it)
            }, {
                view.onSuccess(it)
            }, {
                view.onError(it)
            })
        }
    }
}