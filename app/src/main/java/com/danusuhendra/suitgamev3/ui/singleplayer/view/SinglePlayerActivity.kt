package com.danusuhendra.suitgamev3.ui.singleplayer.view

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.danusuhendra.suitgamev3.BaseApplication
import com.danusuhendra.suitgamev3.R
import com.danusuhendra.suitgamev3.data.database.model.SaveBattle
import com.danusuhendra.suitgamev3.data.prefs.PreferenceHelper
import com.danusuhendra.suitgamev3.repository.SaveBattleRepository
import com.danusuhendra.suitgamev3.ui.singleplayer.model.SinglePlayer
import com.danusuhendra.suitgamev3.ui.singleplayer.presenter.SinglePlayerPresenter
import kotlinx.android.synthetic.main.activity_single_player.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class SinglePlayerActivity : AppCompatActivity(), SinglePlayerView {
    private lateinit var imgClick: List<ImageView>
    private lateinit var compChoose: List<Int>
    private lateinit var userId: String
    private lateinit var token: String
    private var isFavorite = false
    private var state = true
    private var player = 0
    private var comp = 0

    @Inject
    lateinit var presenter: SinglePlayerPresenter

    @Inject
    lateinit var preferenceHelper: PreferenceHelper

    @Inject
    lateinit var saveBattleRepository: SaveBattleRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        (application as BaseApplication).getComponent().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_player)
        presenter.setView(this)
        val actionBar = supportActionBar
        actionBar!!.title = ""
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)

        userId = preferenceHelper.userId!!
        token = preferenceHelper.token!!
        val name = preferenceHelper.username
        tv_pemain.text = name

        imgClick = listOf(iv_batu1, iv_gunting1, iv_kertas1)
        compChoose = listOf(R.id.iv_batu2, R.id.iv_gunting2, R.id.iv_kertas2)

        for (suit in imgClick) {
            suit.setOnClickListener {
                if (state) {
                    val playerName = tv_pemain.text.toString()
                    it.background = getDrawable(R.drawable.select)
                    Log.d("Pemain", suit.contentDescription.toString())
                    comp = compChoose.random()
                    getRandom(comp)
                    player = suit.id.toString().toInt()
                    presenter.getSinglePlayer(
                        SinglePlayer(playerName, player, comp)
                    )
                    state = false
                } else {
                    Toast.makeText(this, "Reset terlebih dahulu", Toast.LENGTH_SHORT).show()
                }
            }
        }

        iv_reset.setOnClickListener {
            reset()
        }

        iv_favorite.setOnClickListener {
            if (!isFavorite) {
                isFavorite = true
                iv_favorite.setImageResource(R.drawable.ic_save_active)
                Toast.makeText(applicationContext, "Favorite Ditambahkan", Toast.LENGTH_SHORT)
                    .show()
                getSaveBattle(userId)
            } else {
                isFavorite = false
                iv_favorite.setImageResource(R.drawable.ic_save)
                Toast.makeText(applicationContext, "Favorite Di Hapus", Toast.LENGTH_SHORT).show()
                deleteSaveBattle(userId)
            }
        }
    }

    override fun getRandom(comp: Int) {
        when (comp) {
            compChoose[0] -> {
                iv_batu2.background = getDrawable(R.drawable.select)
                Log.d("Komputer ", iv_batu2.contentDescription.toString())
            }
            compChoose[1] -> {
                iv_gunting2.background = getDrawable(R.drawable.select)
                Log.d("Komputer ", iv_gunting2.contentDescription.toString())
            }
            compChoose[2] -> {
                iv_kertas2.background = getDrawable(R.drawable.select)
                Log.d("Komputer ", iv_kertas2.contentDescription.toString())
            }
        }
    }

    override fun getResult(result: String) {
        iv_win.apply {
            text = result
            setTextColor(Color.parseColor("#FFFFFF"))
            if (result == "DRAW") {
                textSize = 35f
                setBackgroundColor(Color.parseColor("#5426eb"))
            }else {
                textSize = 25f
                setBackgroundColor(Color.parseColor("#3feb48"))
            }
            iv_favorite.visibility = View.VISIBLE
        }
    }

    override fun reset() {
        val bgReset =
            listOf(iv_batu1, iv_batu2, iv_gunting1, iv_gunting2, iv_kertas1, iv_kertas2, iv_win)
        for (reset in bgReset) {
            reset.background = getDrawable(android.R.color.transparent)
            iv_win.setTextColor(Color.parseColor("#cc0000"))
            iv_win.text = "VS"
            iv_win.textSize = 50f
            iv_favorite.setImageResource(R.drawable.ic_save)
            iv_favorite.visibility = View.GONE
            isFavorite = false
            state = true
            player = 0
            comp = 0
        }
    }

    override fun getSaveBattle(userId: String) {
        val sdf = SimpleDateFormat("MM/dd/yyyy", Locale(localClassName))
        val date = sdf.format(Date())
        val saveBattle = SaveBattle(
            null, userId, date, "Single Player", iv_win.text.toString().replace(Regex("\n"), " ")
        )
        GlobalScope.launch(Dispatchers.Main) {
            saveBattleRepository.insertSaveBattle(saveBattle)
        }
    }

    override fun deleteSaveBattle(userId: String) {
        GlobalScope.launch(Dispatchers.Main) {
            saveBattleRepository.getLatestSaveBattle(userId)
        }
    }

    override fun postResult(result: String) {
        presenter.postBattle(result, token)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}