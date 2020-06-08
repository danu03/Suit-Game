package com.danusuhendra.suitgamev3.ui.singleplayer.presenter

import android.icu.util.LocaleData
import android.util.Log
import com.danusuhendra.suitgamev3.R
import com.danusuhendra.suitgamev3.data.database.model.SaveBattle
import com.danusuhendra.suitgamev3.data.network.model.battle.request.BodyBattle
import com.danusuhendra.suitgamev3.repository.BattleRepository
import com.danusuhendra.suitgamev3.repository.SaveBattleRepository
import com.danusuhendra.suitgamev3.ui.singleplayer.model.SinglePlayer
import com.danusuhendra.suitgamev3.ui.singleplayer.view.SinglePlayerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

class SinglePlayerPresenter(private val repository: BattleRepository) : SinglePlayerPresenterInterface{
    private lateinit var view: SinglePlayerView
    private val playerChoose = mutableListOf<Int>(
        R.id.iv_batu1,
        R.id.iv_gunting1,
        R.id.iv_kertas1,
        R.id.iv_batu2,
        R.id.iv_gunting2,
        R.id.iv_kertas2
    ) //get ID ImageView in activity

    override fun setView(view: SinglePlayerView) {
        this.view = view
    }

    override fun getSinglePlayer(singlePlayer: SinglePlayer) {
        if (singlePlayer.player == playerChoose[0] && singlePlayer.comp == playerChoose[3] ||
            singlePlayer.player == playerChoose[1] && singlePlayer.comp == playerChoose[4] ||
            singlePlayer.player == playerChoose[2] && singlePlayer.comp == playerChoose[5]
        ) {
            view.getResult("DRAW")
            Log.d("Hasil ", "DRAW!")
        } else if (singlePlayer.player == playerChoose[0] && singlePlayer.comp == playerChoose[5] ||
            singlePlayer.player == playerChoose[1] && singlePlayer.comp == playerChoose[3] ||
            singlePlayer.player == playerChoose[2] && singlePlayer.comp == playerChoose[4]
        ) {
            view.getResult("CPU \nMENANG!" )
            Log.d("Hasil ", "CPU MENANG!")
        } else if (singlePlayer.player == playerChoose[0] && singlePlayer.comp == playerChoose[4] ||
            singlePlayer.player == playerChoose[1] && singlePlayer.comp == playerChoose[5] ||
            singlePlayer.player == playerChoose[2] && singlePlayer.comp == playerChoose[3]
        ) {
            view.getResult("${singlePlayer.playerName} \nMENANG!")
            Log.d("Hasil ", "${singlePlayer.playerName} MEMANG!!")
        }
    }

    override fun postBattle(token: String, result : String) {
        GlobalScope.launch(Dispatchers.Main) {
            val mode = "Singleplayer"
            val battle = BodyBattle(mode, result)
            repository.postBattle(battle, token)
        }
    }


}