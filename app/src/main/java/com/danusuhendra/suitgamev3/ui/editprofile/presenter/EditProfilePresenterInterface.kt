package com.danusuhendra.suitgamev3.ui.editprofile.presenter

import android.net.Uri
import com.danusuhendra.suitgamev3.ui.editprofile.view.EditProfileView
import com.danusuhendra.suitgamev3.ui.profile.view.ProfileView

interface EditProfilePresenterInterface {
    fun setView(view : EditProfileView)
    fun getDataProfile(token : String)
    fun updateDataProfile(token: String, username : String, email : String, file : Uri?)
}