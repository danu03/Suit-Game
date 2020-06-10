package com.danusuhendra.suitgamev3.ui.multiplayer.view

interface MultiPlayerView {
    fun getResult(result: String)
    fun reset()
    fun getSaveBattle(userId : String)
    fun deleteSaveBattle(userId: String)
    fun postResult(result: String)
    fun tokenExpired(msg : String?)
}