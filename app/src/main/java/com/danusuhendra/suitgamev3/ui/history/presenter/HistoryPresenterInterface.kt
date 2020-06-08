package com.danusuhendra.suitgamev3.ui.history.presenter

import com.danusuhendra.suitgamev3.ui.history.view.HistoryView

interface HistoryPresenterInterface {
    fun setView(view: HistoryView)
    fun getHistory(token : String)
}