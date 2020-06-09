package com.danusuhendra.suitgamev3.ui.profile.presenter

import com.danusuhendra.suitgamev3.ui.profile.view.ProfileView

interface ProfilePresenterInterface {
    fun setView(view : ProfileView)
    fun getDataProfile(token : String)
}