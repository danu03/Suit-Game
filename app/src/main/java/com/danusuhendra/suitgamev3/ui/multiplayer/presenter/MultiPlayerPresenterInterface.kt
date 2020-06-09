package com.danusuhendra.suitgamev3.ui.multiplayer.presenter

import com.danusuhendra.suitgamev3.ui.multiplayer.model.MultiPlayer
import com.danusuhendra.suitgamev3.ui.multiplayer.view.MultiPlayerView
import com.danusuhendra.suitgamev3.ui.singleplayer.model.SinglePlayer
import com.danusuhendra.suitgamev3.ui.singleplayer.view.SinglePlayerView

interface MultiPlayerPresenterInterface {
    fun setView(view: MultiPlayerView)
    fun getMultiPlayer(multiPlayer: MultiPlayer)
    fun postBattle(result : String, token : String)
}