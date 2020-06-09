package com.danusuhendra.suitgamev3.ui.editprofile.presenter

import android.net.Uri
import com.danusuhendra.suitgamev3.repository.ProfileRepository
import com.danusuhendra.suitgamev3.ui.editprofile.view.EditProfileView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class EditProfilePresenter(private val profileRepository: ProfileRepository) :
    EditProfilePresenterInterface {
    private lateinit var view: EditProfileView
    override fun setView(view: EditProfileView) {
        this.view = view
    }

    override fun getDataProfile(token: String) {
        GlobalScope.launch(Dispatchers.Main) {
            view.showLoading()
            profileRepository.getDataProfile(token, {
                view.getProfile(
                    it.dataUsers?.email.toString(),
                    it.dataUsers?.username.toString(),
                    it.dataUsers?.photo.toString()
                )
                view.hideLoading()
            }, {
                view.onError(it)
                view.hideLoading()
            })
        }
    }

    override fun updateDataProfile(token: String, username: String, email: String, photo: Uri?) {
        GlobalScope.launch(Dispatchers.Main) {
            view.showLoading()
            val requestUsername = username.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val requestEmail = email.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val newFile = File(photo?.path!!)
            val requestFile = newFile.asRequestBody("multipart/form-data".toMediaTypeOrNull())
            val partFile = MultipartBody.Part.createFormData("photo", newFile.name, requestFile)

            profileRepository.editProfile(token, requestUsername, requestEmail, partFile, {
                view.updateProfile(
                    it.dataUsers?.email.toString(),
                    it.dataUsers?.username.toString(),
                    it.dataUsers?.photo.toString()
                )
                view.hideLoading()
            }, {

                view.tokenExpired(it)
                view.hideLoading()
            }, {
                view.onError(it)
                view.hideLoading()
            })
        }
    }

}