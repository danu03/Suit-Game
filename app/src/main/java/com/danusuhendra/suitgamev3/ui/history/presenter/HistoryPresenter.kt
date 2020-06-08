package com.danusuhendra.suitgamev3.ui.history.presenter

import com.danusuhendra.suitgamev3.data.network.ApiService
import com.danusuhendra.suitgamev3.repository.BattleRepository
import com.danusuhendra.suitgamev3.ui.history.view.HistoryView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HistoryPresenter(private val repository: BattleRepository) : HistoryPresenterInterface {
    private lateinit var view : HistoryView
    override fun setView(view: HistoryView) {
        this.view = view
    }

    override fun getHistory(token: String) {
        GlobalScope.launch(Dispatchers.Main) {
            view.showLoading()
            repository.getBattle(token, {
                if (it.size == 0) {
                    view.historyEmpty()
                }else{
                    view.showHistory(it)
                    view.hideLoading()
                }
            }, {
                view.showMessage(it)
                view.hideLoading()
            })
        }
    }


}