package com.danusuhendra.suitgamev3.ui.profile.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.danusuhendra.suitgamev3.BaseApplication
import com.danusuhendra.suitgamev3.R
import com.danusuhendra.suitgamev3.data.prefs.PreferenceHelper
import com.danusuhendra.suitgamev3.ui.editprofile.view.EditProfileActivity
import com.danusuhendra.suitgamev3.ui.login.view.LoginActivity
import com.danusuhendra.suitgamev3.ui.profile.presenter.ProfilePresenter
import kotlinx.android.synthetic.main.fragment_profile.*
import org.jetbrains.anko.image
import javax.inject.Inject

class ProfileFragment : Fragment(), ProfileView {
    @Inject
    lateinit var presenter: ProfilePresenter

    @Inject
    lateinit var preferenceHelper: PreferenceHelper

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as BaseApplication).getComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.setView(this)

        val token = preferenceHelper.token!!
        presenter.getDataProfile(token)

        tv_edit.setOnClickListener {
            startActivity(Intent(context, EditProfileActivity::class.java))
        }
    }


    override fun showLoading() {
        pb_profile.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pb_profile.visibility = View.GONE
    }

    override fun getProfile(email: String, username: String, photo: String) {
        edt_email.text = email
        edt_username.text = username
        Glide.with(this)
            .load(photo)
            .placeholder(R.drawable.blankprofile)
            .into(iv_profile)
    }

    override fun onError(error: String) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
        preferenceHelper.token = ""
        preferenceHelper.username = ""
        preferenceHelper.userId = ""
        startActivity(Intent(context, LoginActivity::class.java))
        requireActivity().finish()
    }


    override fun onStart() {
        super.onStart()
        presenter.getDataProfile(preferenceHelper.token!!)
    }

}