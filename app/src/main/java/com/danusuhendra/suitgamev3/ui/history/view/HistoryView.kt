package com.danusuhendra.suitgamev3.ui.history.view

import com.danusuhendra.suitgamev3.data.network.model.battle.response.DataGetBattle

interface HistoryView {
    fun historyEmpty()
    fun showHistory(result : MutableList<DataGetBattle>)
    fun showMessage(msg : String?)
    fun hideLoading()
    fun showLoading()
}