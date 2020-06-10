package com.danusuhendra.suitgamev3.ui.profile.view

interface ProfileView {
    fun showLoading()
    fun hideLoading()
    fun getProfile(email : String, username : String, photo : String)
    fun onError(error : String)
}