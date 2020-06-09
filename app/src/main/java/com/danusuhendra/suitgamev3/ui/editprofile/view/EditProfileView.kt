package com.danusuhendra.suitgamev3.ui.editprofile.view

import android.net.Uri

interface EditProfileView {
    fun showLoading()
    fun hideLoading()
    fun showDialogEditEmail()
    fun showDialogEditUsername()
    fun showDialogEditValidation()
    fun getProfile(email : String, username : String, photo : String)
    fun updateProfile(email : String, username: String, photo: String)
    fun onError(error : String)
    fun tokenExpired(msg : String)
}