package com.danusuhendra.suitgamev3.ui.history.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.danusuhendra.suitgamev3.BaseApplication
import com.danusuhendra.suitgamev3.R
import com.danusuhendra.suitgamev3.adapter.HistoryAdapter
import com.danusuhendra.suitgamev3.data.network.model.battle.response.DataGetBattle
import com.danusuhendra.suitgamev3.data.prefs.PreferenceHelper
import com.danusuhendra.suitgamev3.ui.history.presenter.HistoryPresenter
import com.danusuhendra.suitgamev3.ui.login.view.LoginActivity
import kotlinx.android.synthetic.main.fragment_history.*
import javax.inject.Inject

class HistoryFragment : Fragment(), HistoryView {

    private lateinit var adapter : HistoryAdapter
    private lateinit var listHistory : MutableList<DataGetBattle>

    @Inject
    lateinit var presenter : HistoryPresenter

    @Inject
    lateinit var preferenceHelper: PreferenceHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val token = preferenceHelper.token!!
        presenter.setView(this)
        listHistory = mutableListOf()
        adapter = HistoryAdapter(listHistory)
        presenter.getHistory(token)
        rv_history.layoutManager = LinearLayoutManager(context)
        rv_history.adapter = adapter
    }

    override fun onAttach(context: Context) {
        (activity?.application as BaseApplication).getComponent().inject(this)
        super.onAttach(context)
    }

    override fun historyEmpty() {
        rv_history.visibility = View.GONE
        tv_empty.visibility = View.VISIBLE
    }

    override fun showHistory(result: MutableList<DataGetBattle>) {
        rv_history.visibility = View.VISIBLE
        tv_empty.visibility = View.GONE
        listHistory.clear()
        listHistory.addAll(result)
        adapter.notifyDataSetChanged()
    }

    override fun showMessage(msg: String?) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        preferenceHelper.token = ""
        preferenceHelper.username = ""
        preferenceHelper.userId = ""
        startActivity(Intent(context, LoginActivity::class.java))
        requireActivity().finish()
    }

    override fun hideLoading() {
        pb_history.visibility = View.GONE
    }

    override fun showLoading() {
        pb_history.visibility = View.VISIBLE
    }
}