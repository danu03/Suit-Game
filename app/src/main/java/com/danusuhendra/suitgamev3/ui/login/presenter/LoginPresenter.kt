package com.danusuhendra.suitgamev3.ui.login.presenter

import com.danusuhendra.suitgamev3.repository.AuthRepository
import com.danusuhendra.suitgamev3.ui.login.view.LoginView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class LoginPresenter(private val repository: AuthRepository) : LoginPresenterInterface {
    private lateinit var view : LoginView

    fun setView(view: LoginView){
        this.view = view
    }

    override fun login(email: String, password: String) {
        GlobalScope.launch(Dispatchers.Main) {
            view.showLoading()
            repository.login(email, password, {
                view.showResult(it)
                view.hideLoading()
            }, {
                view.onError(it)
                view.hideLoading()
            })
        }
    }
}