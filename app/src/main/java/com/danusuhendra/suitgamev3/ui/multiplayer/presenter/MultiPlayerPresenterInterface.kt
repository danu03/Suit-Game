package com.danusuhendra.suitgamev3.ui.multiplayer.presenter

import com.danusuhendra.suitgamev3.ui.multiplayer.model.MultiPlayer
import com.danusuhendra.suitgamev3.ui.multiplayer.view.MultiPlayerView

interface MultiPlayerPresenterInterface {
    fun setView(view: MultiPlayerView)
    fun getMultiPlayer(multiPlayer: MultiPlayer)
    fun postBattle(result : String, token : String)
}