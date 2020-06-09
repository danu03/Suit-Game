package com.danusuhendra.suitgamev3.ui.singleplayer.presenter

import com.danusuhendra.suitgamev3.ui.singleplayer.model.SinglePlayer
import com.danusuhendra.suitgamev3.ui.singleplayer.view.SinglePlayerView

interface SinglePlayerPresenterInterface {
    fun setView(view: SinglePlayerView)
    fun getSinglePlayer(singlePlayer : SinglePlayer)
    fun postBattle(result : String, token : String)
}