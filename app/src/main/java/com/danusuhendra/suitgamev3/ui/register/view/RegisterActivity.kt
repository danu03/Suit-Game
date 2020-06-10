package com.danusuhendra.suitgamev3.ui.register.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.util.Patterns
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.danusuhendra.suitgamev3.BaseApplication
import com.danusuhendra.suitgamev3.R
import com.danusuhendra.suitgamev3.data.network.ApiResource
import com.danusuhendra.suitgamev3.data.network.model.users.response.ResponseUsers
import com.danusuhendra.suitgamev3.repository.AuthRepository
import com.danusuhendra.suitgamev3.ui.login.view.LoginActivity
import com.danusuhendra.suitgamev3.ui.register.presenter.RegisterPresenter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.contentView
import javax.inject.Inject

class RegisterActivity : AppCompatActivity(), RegisterView {
    @Inject
    lateinit var presenter: RegisterPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        (application as BaseApplication).getComponent().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        presenter = RegisterPresenter(AuthRepository(ApiResource.apiServices()))
        presenter.setView(this)

        btn_register.setOnClickListener {
            val username = edt_username.text.toString()
            val email = edt_email.text.toString()
            val password = edt_password.text.toString()

            when {
                username.isEmpty() || username.length < 6 -> {
                    edt_username.error = "Username minimal 6 karakter!"
                    edt_username.isFocusable = true
                }
                email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                    edt_email.error = "Masukkan email dengan benar!"
                    edt_email.isFocusable = true
                }
                password.isEmpty() || password.length < 4 -> {
                    edt_password.error = "Password tidak boleh kosong dan kurang dari 4 digit!"
                    edt_password.isFocusable = true
                }
                else -> {
                    presenter.register(email, password, username)
                }
            }
        }

        btn_reset.setOnClickListener {
            edt_username.text.clear()
            edt_email.text.clear()
            edt_password.text.clear()
        }

        img_show.setOnClickListener {
            if (edt_password.transformationMethod == PasswordTransformationMethod.getInstance()) {
                img_show.setImageResource(R.drawable.ic_visibility_black_24dp)
                edt_password.transformationMethod = HideReturnsTransformationMethod.getInstance()
            } else {
                img_show.setImageResource(R.drawable.ic_visibility_off_black_24dp)
                edt_password.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }

        tv_login.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    override fun showResult(data: ResponseUsers) {
        Log.d("Register", data.toString())
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    override fun onError(it: String?) {
        Log.d("Register", it)
        val snack = Snackbar.make(contentView!!, it.toString(), Snackbar.LENGTH_SHORT)
            .setTextColor(Color.RED)
        snack.view.setBackgroundColor(Color.WHITE)
        snack.show()
    }

    override fun hideLoading() {
        pb_register.visibility = View.GONE
    }

    override fun showLoading() {
        pb_register.visibility = View.VISIBLE
    }
}