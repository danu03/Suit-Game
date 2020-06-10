package com.danusuhendra.suitgamev3.ui.login.view

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import com.danusuhendra.suitgamev3.BaseApplication
import com.danusuhendra.suitgamev3.R
import com.danusuhendra.suitgamev3.data.network.ApiResource
import com.danusuhendra.suitgamev3.data.network.model.users.response.ResponseUsers
import com.danusuhendra.suitgamev3.data.prefs.PreferenceHelper
import com.danusuhendra.suitgamev3.repository.AuthRepository
import com.danusuhendra.suitgamev3.ui.MainActivity
import com.danusuhendra.suitgamev3.ui.login.presenter.LoginPresenter
import com.danusuhendra.suitgamev3.ui.register.view.RegisterActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.contentView
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), LoginView {
    @Inject
    lateinit var presenter: LoginPresenter

    @Inject
    lateinit var preferenceHelper: PreferenceHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        (application as BaseApplication).getComponent().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter = LoginPresenter(AuthRepository(ApiResource.apiServices()))
        presenter.setView(this)
        btn_login.setOnClickListener {
            when {
                edt_email.text.isNullOrEmpty() -> {
                    edt_email.error = "Email harus diisi"
                    edt_email.isFocusable = true
                }
                edt_password.text.isNullOrEmpty() -> {
                    edt_password.error = "Password harus diisi"
                    edt_password.isFocusable = true
                }
                else -> {
                    val email = edt_email.text.toString()
                    val password = edt_password.text.toString()
                    presenter.login(email, password)
                }
            }
        }
        btn_reset.setOnClickListener {
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

        tv_register.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }
    }

    override fun showResult(data: ResponseUsers) {
        Log.d("Login", data.toString())
        val intent = Intent(this, MainActivity::class.java)
        preferenceHelper.username = data.dataUsers?.username?.capitalize()
        preferenceHelper.userId = data.dataUsers?.id!!
        preferenceHelper.token = "Bearer ${data.dataUsers.token}"
        startActivity(intent)
        finish()
    }

    override fun onError(it: String?) {
        Log.d("Login", it.toString())
        val snack = Snackbar.make(contentView!!, it.toString(), Snackbar.LENGTH_SHORT)
            .setTextColor(Color.RED)
        snack.view.setBackgroundColor(Color.WHITE)
        snack.show()
    }

    override fun hideLoading() {
        pb_login.visibility = View.GONE

    }

    override fun showLoading() {
        pb_login.visibility = View.VISIBLE
    }

//    private fun login() = GlobalScope.launch(Dispatchers.Main) {
//        val email = edt_email_login.text.toString()
//        val password = edt_password.text.toString()
//        repository.login(email, password){
//            Toast.makeText(this@LoginActivity, it, Toast.LENGTH_SHORT).show()
//        }
//    }
}