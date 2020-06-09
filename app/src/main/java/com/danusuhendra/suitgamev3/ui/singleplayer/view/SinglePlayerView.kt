package com.danusuhendra.suitgamev3.ui.singleplayer.view

interface SinglePlayerView {
    fun getRandom(comp: Int)
    fun getResult(result: String)
    fun reset()
    fun getSaveBattle(userId : String)
    fun deleteSaveBattle(userId: String)
    fun postResult(result: String)
}