package com.danusuhendra.suitgamev3.ui.landingpage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.danusuhendra.suitgamev3.R
import com.danusuhendra.suitgamev3.adapter.ViewPagerAdapter
import com.danusuhendra.suitgamev3.data.prefs.PreferenceHelper
import com.danusuhendra.suitgamev3.ui.login.view.LoginActivity
import kotlinx.android.synthetic.main.activity_landing_page.*

class LandingPageActivity : AppCompatActivity() {
    var indexPage = 0
    private lateinit var preferenceHelper: PreferenceHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_page)
        preferenceHelper = PreferenceHelper(this)

        val adapter = ViewPagerAdapter(supportFragmentManager)
        view_pager.adapter = adapter
        dots_indicator.setViewPager(view_pager)

        ivnext.visibility = View.GONE
        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                indexPage = position
                if (indexPage > 0) {
                    ivnext.visibility = View.VISIBLE
                }
            }
        })
        ivnext.setOnClickListener {
            if (indexPage == 1){
                view_pager.currentItem = indexPage + 1
            } else if (indexPage == 2){
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
    }
}