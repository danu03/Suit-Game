package com.danusuhendra.suitgamev3.ui.savegame.view

import com.danusuhendra.suitgamev3.data.database.model.SaveBattle

interface SaveBattleView {
    fun showSaveBattle(result : MutableList<SaveBattle>)
    fun saveBattleEmpty()
    fun deleteSaveBattle(saveBattle: SaveBattle, userId : String)
}