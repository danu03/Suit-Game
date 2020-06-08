package com.danusuhendra.suitgamev3.ui.register.presenter

import com.danusuhendra.suitgamev3.repository.AuthRepository
import com.danusuhendra.suitgamev3.ui.register.view.RegisterView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RegisterPresenter(private val repository: AuthRepository) {
    private lateinit var view : RegisterView

    fun setView(view : RegisterView) {
        this.view = view
    }

    fun register (email: String, password: String, username : String) {
        GlobalScope.launch(Dispatchers.Main) {
            view.showLoading()
            repository.register(email, password, username, {
                view.showResult(it)
                view.hideLoading()
            },{
                view.onError(it)
                view.hideLoading()
            })
        }
    }
}