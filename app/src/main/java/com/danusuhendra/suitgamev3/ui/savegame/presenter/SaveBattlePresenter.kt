package com.danusuhendra.suitgamev3.ui.savegame.presenter

import com.danusuhendra.suitgamev3.data.database.model.SaveBattle
import com.danusuhendra.suitgamev3.repository.SaveBattleRepository
import com.danusuhendra.suitgamev3.ui.savegame.view.SaveBattleView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SaveBattlePresenter(private val repository: SaveBattleRepository) : SaveBattlePresenterInterface {
    private lateinit var view : SaveBattleView
    override fun setView(view: SaveBattleView) {
        this.view = view
    }

    override fun checkSaveBattleById(userId: String) {
        GlobalScope.launch(Dispatchers.Main) {
            repository.getSaveByUserId(userId) {
                onGetSaveBattle(it)
            }
        }
    }

    override fun onGetSaveBattle(listSaveBattle: MutableList<SaveBattle>) {
        if (listSaveBattle.size == 0){
            view.saveBattleEmpty()
        }else{
            view.showSaveBattle(listSaveBattle)
        }
    }

    override fun deleteSaveBattle(saveBattle: SaveBattle, userId: String) {
        GlobalScope.launch(Dispatchers.Main) {
            repository.deleteSaveBattle(saveBattle)
            repository.getSaveByUserId(userId) {
                onGetSaveBattle(it)
            }
        }
    }

}