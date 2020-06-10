package com.danusuhendra.suitgamev3.ui.editprofile.view

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.danusuhendra.suitgamev3.BaseApplication
import com.danusuhendra.suitgamev3.R
import com.danusuhendra.suitgamev3.data.prefs.PreferenceHelper
import com.danusuhendra.suitgamev3.ui.editprofile.presenter.EditProfilePresenter
import com.danusuhendra.suitgamev3.ui.login.view.LoginActivity
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.activity_edit_profile.iv_profile
import kotlinx.android.synthetic.main.activity_edit_profile.view.*
import org.jetbrains.anko.email
import javax.inject.Inject

class EditProfileActivity : AppCompatActivity(), EditProfileView{
    @Inject
    lateinit var presenter : EditProfilePresenter
    @Inject
    lateinit var preferenceHelper : PreferenceHelper

    private lateinit var dialog: AlertDialog
    private var fileUri : Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        (application as BaseApplication).getComponent().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        presenter.setView(this)

        val token = preferenceHelper.token!!
        presenter.getDataProfile(token)

        val actionBar = supportActionBar
        actionBar!!.title = ""
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)

        iv_edit_photo.setOnClickListener {
            CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(this)
        }

        iv_edit_email.setOnClickListener {
            showDialogEditEmail()
        }

        iv_edit_username.setOnClickListener {
            showDialogEditUsername()
        }

        btn_save.setOnClickListener {
            if (fileUri == null){
                Toast.makeText(this, "Photo must be filled in", Toast.LENGTH_SHORT).show()
            }else{
                showDialogEditValidation()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == Activity.RESULT_OK) {
                fileUri = result.uri
                iv_profile.setImageURI(fileUri)
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                val error = result.error
            }
        }
    }

    override fun showLoading() {
        pb_edit_profile.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pb_edit_profile.visibility = View.GONE
    }

    override fun showDialogEditEmail() {
        this.let {
            val builder = AlertDialog.Builder(this)
            val view = layoutInflater.inflate(R.layout.update_dialog_email, null)

            builder.setView(view)
            builder.setPositiveButton("UPDATE") { _, _ ->
                val email = view.tv_email.text.toString()
                tv_email.text = email
            }
            builder.setNegativeButton("BATAL") { dialog, _ ->
                dialog.cancel()
            }
            dialog = builder.create()
            dialog.show()
        }
    }

    override fun showDialogEditUsername() {
        this.let {
            val builder = AlertDialog.Builder(this)
            val view = layoutInflater.inflate(R.layout.update_dialog_username, null)

            builder.setView(view)
            builder.setPositiveButton("OK") { _, _ ->
                val username = view.tv_username.text.toString()
                tv_username.text = username
            }
            builder.setNegativeButton("BATAL") { dialog, _ ->
                dialog.cancel()
            }
            dialog = builder.create()
            dialog.show()
        }
    }

    override fun showDialogEditValidation() {
        this.let {
            val builder = AlertDialog.Builder(this)
            val view = layoutInflater.inflate(R.layout.update_dialog_validation, null)

            builder.setView(view)
            builder.setPositiveButton("OK") { _, _ ->
                val email = tv_email.text.toString()
                val username = tv_username.text.toString()
                presenter.updateDataProfile(preferenceHelper.token!!, username, email, fileUri)
            }
            builder.setNegativeButton("BATAL") { dialog, _ ->
                dialog.cancel()
            }
            dialog = builder.create()
            dialog.show()
        }
    }

    override fun getProfile(email: String, username: String, photo: String) {
        tv_email.text = email
        tv_username.text = username
        Glide.with(this)
            .load(photo)
            .placeholder(R.drawable.blank_profile)
            .into(iv_profile)
    }

    override fun updateProfile(email: String, username: String, photo: String) {
        tv_email.text = email
        tv_username.text = username
        Glide.with(this)
            .load(photo)
            .placeholder(R.drawable.blank_profile)
            .into(iv_profile)
        preferenceHelper.username = username
    }

    override fun onError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()

    }

    override fun tokenExpired(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        preferenceHelper.token = ""
        preferenceHelper.username = ""
        preferenceHelper.userId = ""
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}