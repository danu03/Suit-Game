package com.danusuhendra.suitgamev3.adapter

import android.icu.text.SimpleDateFormat
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.danusuhendra.suitgamev3.R
import com.danusuhendra.suitgamev3.data.network.model.battle.response.DataGetBattle
import kotlinx.android.synthetic.main.item_history.view.*
import java.util.*

class HistoryAdapter(private val listHistory : MutableList<DataGetBattle>) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listHistory.size

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: HistoryAdapter.ViewHolder, position: Int) {
        holder.bind(listHistory[position])
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        @RequiresApi(Build.VERSION_CODES.N)
        fun bind(history : DataGetBattle) {
            itemView.tv_date_history.text = history.createdAt!!.parseDate()
            itemView.tv_mode_history.text = history.mode
            itemView.tv_result_history.text = history.message
        }
        @RequiresApi(Build.VERSION_CODES.N)
        private fun String.parseDate() : String{
            val inputFormat =  SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.UK)
            val date = inputFormat.parse(this)
            val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale("ID"))
            return outputFormat.format(date)
        }
    }

}