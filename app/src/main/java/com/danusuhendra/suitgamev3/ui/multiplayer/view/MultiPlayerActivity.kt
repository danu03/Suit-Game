package com.danusuhendra.suitgamev3.ui.multiplayer.view

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.danusuhendra.suitgamev3.BaseApplication
import com.danusuhendra.suitgamev3.R
import com.danusuhendra.suitgamev3.data.database.model.SaveBattle
import com.danusuhendra.suitgamev3.data.prefs.PreferenceHelper
import com.danusuhendra.suitgamev3.repository.SaveBattleRepository
import com.danusuhendra.suitgamev3.ui.multiplayer.model.MultiPlayer
import com.danusuhendra.suitgamev3.ui.multiplayer.presenter.MultiPlayerPresenter
import com.danusuhendra.suitgamev3.ui.singleplayer.presenter.SinglePlayerPresenter
import kotlinx.android.synthetic.main.activity_multi_player.*
import kotlinx.android.synthetic.main.activity_multi_player.iv_batu1
import kotlinx.android.synthetic.main.activity_multi_player.iv_batu2
import kotlinx.android.synthetic.main.activity_multi_player.iv_favorite
import kotlinx.android.synthetic.main.activity_multi_player.iv_gunting1
import kotlinx.android.synthetic.main.activity_multi_player.iv_gunting2
import kotlinx.android.synthetic.main.activity_multi_player.iv_kertas1
import kotlinx.android.synthetic.main.activity_multi_player.iv_kertas2
import kotlinx.android.synthetic.main.activity_multi_player.iv_reset
import kotlinx.android.synthetic.main.activity_multi_player.iv_win
import kotlinx.android.synthetic.main.activity_single_player.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class MultiPlayerActivity : AppCompatActivity(), MultiPlayerView {
    private lateinit var player1Choose: List<ImageView>
    private lateinit var player2Choose: List<ImageView>
    private lateinit var userId: String
    private lateinit var token: String
    private var isFavorite = false
    private var state = true
    private var state2 = true
    private var player1 = 0
    private var player2 = 0

    @Inject
    lateinit var presenter: MultiPlayerPresenter

    @Inject
    lateinit var preferenceHelper: PreferenceHelper

    @Inject
    lateinit var saveBattleRepository: SaveBattleRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        (application as BaseApplication).getComponent().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multi_player)

        val actionBar = supportActionBar
        actionBar!!.title = ""
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)

        presenter.setView(this)
        userId = preferenceHelper.userId!!
        token = preferenceHelper.token!!

        val name = preferenceHelper.username!!
        tv_player.text = name

        player1Choose = listOf(iv_batu1, iv_gunting1, iv_kertas1)
        player2Choose = listOf(iv_batu2, iv_gunting2, iv_kertas2)

        for (suit in player1Choose) {
            suit.setOnClickListener {
                if (state) {
                    val playerName = tv_player.text.toString()
                    it.background = getDrawable(R.drawable.select)
                    Log.d("$playerName", suit.contentDescription.toString())
                    player1 = suit.id.toString().toInt()
                    state = false
                } else {
                    Toast.makeText(this, "Reset terlebih dahulu", Toast.LENGTH_SHORT).show()
                }
            }
        }
        for (suit in player2Choose) {
            suit.setOnClickListener {
                if (state2 && !state) {
                    val playerName = tv_player.text.toString()
                    it.background = getDrawable(R.drawable.select)
                    Log.d("Pemain 2 ", suit.contentDescription.toString())
                    player2 = suit.id.toString().toInt()
                    presenter.getMultiPlayer(
                        MultiPlayer(
                            playerName,
                            player1,
                            player2
                        )
                    )
                    state2 = false
                } else if (state2 == false) {
                    Toast.makeText(this, "Reset terlebih dahulu", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Pemain 1 harus memilih duluan", Toast.LENGTH_SHORT).show()
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

    override fun getResult(result: String) {
        iv_win.apply {
            text = result
            setTextColor(Color.parseColor("#FFFFFF"))
            if (result == "DRAW") {
                presenter.postBattle(token, "Draw")
                textSize = 35f
                setBackgroundColor(Color.parseColor("#5426eb"))
            } else if (result == "PEMAIN 2 \nMENANG!") {
                presenter.postBattle(token, "Opponent Win")
                textSize = 25f
                setBackgroundColor(Color.parseColor("#3feb48"))
            } else {
                presenter.postBattle(token, "Player Win")
                textSize = 25f
                setBackgroundColor(Color.parseColor("#3feb48"))
            }
            iv_favorite.visibility = View.VISIBLE
        }
    }

    override fun reset() {
        val bgreset =
            listOf(iv_batu1, iv_batu2, iv_gunting1, iv_gunting2, iv_kertas1, iv_kertas2, iv_win)
        for (reset in bgreset) {
            reset.background = getDrawable(android.R.color.transparent)
            iv_win.setTextColor(Color.parseColor("#cc0000"))
            iv_win.text = "VS"
            iv_win.textSize = 50f
            iv_favorite.setImageResource(R.drawable.ic_save)
            iv_favorite.visibility = View.GONE
            isFavorite = false
            state = true
            state2 = true
            player1 = 0
            player2 = 0
        }
    }

    override fun getSaveBattle(userId: String) {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale(localClassName))
        val date = sdf.format(Date())
        val saveBattle = SaveBattle(
            null, userId, date, "Multi Player" ,iv_win.text.toString().replace(Regex("\n"), " ")
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

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}