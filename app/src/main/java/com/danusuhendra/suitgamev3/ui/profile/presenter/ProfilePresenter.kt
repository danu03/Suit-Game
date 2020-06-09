package com.danusuhendra.suitgamev3.ui.profile.presenter

import com.danusuhendra.suitgamev3.repository.ProfileRepository
import com.danusuhendra.suitgamev3.ui.profile.view.ProfileView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProfilePresenter(private val profileRepository: ProfileRepository) : ProfilePresenterInterface {
    private lateinit var view: ProfileView
    override fun setView(view: ProfileView) {
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
            },{
                view.onError(it)
                view.hideLoading()
            })
        }
    }

}