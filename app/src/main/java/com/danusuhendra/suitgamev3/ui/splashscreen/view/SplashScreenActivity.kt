package com.danusuhendra.suitgamev3.ui.splashscreen.view

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.danusuhendra.suitgamev3.BaseApplication
import com.danusuhendra.suitgamev3.R
import com.danusuhendra.suitgamev3.data.network.model.users.response.ResponseAuthMe
import com.danusuhendra.suitgamev3.data.prefs.PreferenceHelper
import com.danusuhendra.suitgamev3.ui.MainActivity
import com.danusuhendra.suitgamev3.ui.landingpage.LandingPageActivity
import com.danusuhendra.suitgamev3.ui.splashscreen.presenter.SplashScreenPresenter
import javax.inject.Inject

class SplashScreenActivity : AppCompatActivity(), SplashScreenView {
    @Inject
    lateinit var presenter: SplashScreenPresenter

    @Inject
    lateinit var preferenceHelper: PreferenceHelper

    private lateinit var mediaPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        (application as BaseApplication).getComponent().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        presenter.setView(this)

        mediaPlayer = MediaPlayer.create(this, R.raw.intro)
        mediaPlayer.start()

        Handler().postDelayed({
            val token = preferenceHelper.token
            presenter.userAuth(token!!)
        }, 3000)
    }

    override fun isLogin(status: Boolean) {
        if (!status) {
            startActivity(Intent(this, LandingPageActivity::class.java))
            mediaPlayer.stop()
            finish()
        } else {
            startActivity(Intent(this, MainActivity::class.java))
            mediaPlayer.stop()
            finish()
        }
    }

    override fun onSuccess(data: ResponseAuthMe) {
        Log.d("SplashScreen", data.toString())
    }

    override fun onError(it: String?) {
        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        Log.d("SplashScreen", it)
    }
}