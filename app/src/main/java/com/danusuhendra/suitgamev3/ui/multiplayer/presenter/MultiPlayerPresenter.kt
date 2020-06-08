package com.danusuhendra.suitgamev3.ui.multiplayer.presenter

import android.util.Log
import com.danusuhendra.suitgamev3.R
import com.danusuhendra.suitgamev3.data.network.model.battle.request.BodyBattle
import com.danusuhendra.suitgamev3.repository.BattleRepository
import com.danusuhendra.suitgamev3.ui.multiplayer.model.MultiPlayer
import com.danusuhendra.suitgamev3.ui.multiplayer.view.MultiPlayerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MultiPlayerPresenter(private val repository: BattleRepository) : MultiPlayerPresenterInterface {
    private lateinit var view : MultiPlayerView

    private val playerChoose = mutableListOf<Int>(
        R.id.iv_batu1,
        R.id.iv_gunting1,
        R.id.iv_kertas1,
        R.id.iv_batu2,
        R.id.iv_gunting2,
        R.id.iv_kertas2
    ) //get ID ImageView in activity

    override fun setView(view: MultiPlayerView) {
        this.view = view
    }

    override fun getMultiPlayer(multiPlayer: MultiPlayer) {
        if (multiPlayer.player == playerChoose[0] && multiPlayer.player2 == playerChoose[3] ||
            multiPlayer.player == playerChoose[1] && multiPlayer.player2 == playerChoose[4] ||
            multiPlayer.player == playerChoose[2] && multiPlayer.player2 == playerChoose[5]
        ) {
            view.getResult("DRAW")
            Log.d("Hasil ", "DRAW!")
        } else if (multiPlayer.player == playerChoose[0] && multiPlayer.player2 == playerChoose[5] ||
            multiPlayer.player == playerChoose[1] && multiPlayer.player2 == playerChoose[3] ||
            multiPlayer.player == playerChoose[2] && multiPlayer.player2 == playerChoose[4]
        ) {
            view.getResult("PEMAIN 2 \nMENANG!")
            Log.d("Hasil ", "PEMAIN 2 MENANG!")
        } else if (multiPlayer.player == playerChoose[0] && multiPlayer.player2 == playerChoose[4] ||
            multiPlayer.player == playerChoose[1] && multiPlayer.player2 == playerChoose[5] ||
            multiPlayer.player == playerChoose[2] && multiPlayer.player2 == playerChoose[3]
        ) {
            view.getResult("${multiPlayer.playerName} \nMENANG!")
            Log.d("Hasil ", "${multiPlayer.playerName} MEMANG!!")
        }
    }

    override fun postBattle(token: String, result: String) {
        GlobalScope.launch(Dispatchers.Main) {
            val mode = "Multiplayer"
            val battle = BodyBattle(mode, result)
            repository.postBattle(battle, token)
        }
    }
}