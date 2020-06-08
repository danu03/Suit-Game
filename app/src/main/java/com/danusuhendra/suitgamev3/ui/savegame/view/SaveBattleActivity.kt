package com.danusuhendra.suitgamev3.ui.savegame.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.danusuhendra.suitgamev3.BaseApplication
import com.danusuhendra.suitgamev3.R
import com.danusuhendra.suitgamev3.adapter.SaveBattleAdapter
import com.danusuhendra.suitgamev3.data.database.model.SaveBattle
import com.danusuhendra.suitgamev3.data.prefs.PreferenceHelper
import com.danusuhendra.suitgamev3.ui.savegame.presenter.SaveBattlePresenter
import kotlinx.android.synthetic.main.activity_save_battle.*
import javax.inject.Inject

class SaveBattleActivity : AppCompatActivity(), SaveBattleView {
    @Inject
    lateinit var presenter: SaveBattlePresenter

    @Inject
    lateinit var preferenceHelper: PreferenceHelper

    private lateinit var saveBattleList: MutableList<SaveBattle>
    private lateinit var adapter: SaveBattleAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        (application as BaseApplication).getComponent().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_battle)

        val actionBar = supportActionBar
        actionBar!!.title = ""
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)

        val userId = preferenceHelper.userId!!
        presenter.setView(this)
        saveBattleList = mutableListOf()
        adapter = SaveBattleAdapter(saveBattleList)

        adapter.setOnClickListener(object : SaveBattleAdapter.OnClickListenerCallback<SaveBattle> {
            override fun onDeleteClick(data: SaveBattle, position: Int) {
                deleteSaveBattle(data, userId)
            }
        })

        presenter.checkSaveBattleById(userId)

        rv_save_battle.layoutManager = LinearLayoutManager(this)
        rv_save_battle.adapter = adapter

    }

    override fun showSaveBattle(result: MutableList<SaveBattle>) {
        tv_empty.visibility = View.GONE
        rv_save_battle.visibility = View.VISIBLE
        saveBattleList.clear()
        saveBattleList.addAll(result)
        adapter.notifyDataSetChanged()
    }

    override fun saveBattleEmpty() {
        tv_empty.visibility = View.VISIBLE
        rv_save_battle.visibility = View.GONE
    }

    override fun deleteSaveBattle(saveBattle: SaveBattle, userId: String) {
        presenter.deleteSaveBattle(saveBattle, userId)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}