package com.danusuhendra.suitgamev3.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.danusuhendra.suitgamev3.R
import com.danusuhendra.suitgamev3.ui.history.view.HistoryFragment
import com.danusuhendra.suitgamev3.ui.mainmenu.MainMenuFragment
import com.danusuhendra.suitgamev3.ui.profile.view.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigateFragment(MainMenuFragment())
        bottom_navigation.itemIconTintList = null

        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu_battle -> navigateFragment(MainMenuFragment())
                R.id.menu_history -> navigateFragment(HistoryFragment())
                R.id.menu_profile -> navigateFragment(ProfileFragment())
            }
            true
        }
    }

    private fun navigateFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, fragment)
            commit()
        }
    }

    override fun onBackPressed() {
        val alertDialog = this.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setPositiveButton("YA") { _, _ ->
                    finishAffinity()
                }
                setNegativeButton("TIDAK") { dialog, _ ->
                    dialog.cancel()
                }
                setTitle("KELUAR")
                setMessage("Yakin Mau Keluar ?")
            }
            builder.create()
        }
        alertDialog.show()
    }
}