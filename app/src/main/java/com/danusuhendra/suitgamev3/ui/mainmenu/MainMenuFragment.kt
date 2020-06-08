package com.danusuhendra.suitgamev3.ui.mainmenu

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import com.bumptech.glide.Glide
import com.danusuhendra.suitgamev3.R
import com.danusuhendra.suitgamev3.data.prefs.PreferenceHelper
import com.danusuhendra.suitgamev3.ui.login.view.LoginActivity
import com.danusuhendra.suitgamev3.ui.multiplayer.view.MultiPlayerActivity
import com.danusuhendra.suitgamev3.ui.savegame.view.SaveBattleActivity
import com.danusuhendra.suitgamev3.ui.singleplayer.view.SinglePlayerActivity
import kotlinx.android.synthetic.main.fragment_main_menu.*
import kotlin.system.exitProcess

class MainMenuFragment : Fragment() {
    private lateinit var preferenceHelper: PreferenceHelper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_menu, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preferenceHelper = PreferenceHelper(requireContext())

        val popupMenu = PopupMenu(context, iv_more)
        popupMenu.inflate(R.menu.menu_main)
        iv_more.setOnClickListener {
            popupMenu.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.menu_save_battle -> {
                        startActivity(Intent(context, SaveBattleActivity::class.java))
                    }
                    R.id.menu_exit -> {
                        exitProcess(0)
                    }
                    R.id.menu_logout -> {
                        preferenceHelper.token = ""
                        preferenceHelper.username = ""
                        preferenceHelper.userId = ""
                        startActivity(Intent(context, LoginActivity::class.java))
                        requireActivity().finish()
                    }
                }
                true
            }
            popupMenu.show()
        }

        val username = preferenceHelper.username
        tv_vs_comp.text = "$username vs CPU"
        tv_vs_player.text = "$username vs Pemain"

        Glide.with(view)
            .load("https://docs.google.com/uc?id=1u2hdu9hxd79aeC6hGDJ1s23Vsce3I_07")
            .into(image_vs_player)

        Glide.with(view)
            .load("https://docs.google.com/uc?id=1RrJN4h-Sv6IRimIQQEbfis6ujKobdfxn")
            .into(image_vs_comp)

        image_vs_comp.setOnClickListener {
            val intent = Intent(context, MultiPlayerActivity::class.java)
            startActivity(intent)
        }

        image_vs_player.setOnClickListener {
            val intent = Intent(context, SinglePlayerActivity::class.java)
            startActivity(intent)
        }
    }

}