package com.danusuhendra.suitgamev3.ui.savegame.presenter

import com.danusuhendra.suitgamev3.data.database.model.SaveBattle
import com.danusuhendra.suitgamev3.ui.savegame.view.SaveBattleView

interface SaveBattlePresenterInterface {
    fun setView(view : SaveBattleView)
    fun checkSaveBattleById(userId : String)
    fun onGetSaveBattle(listSaveBattle : MutableList<SaveBattle>)
    fun deleteSaveBattle(saveBattle: SaveBattle, userId: String)
}