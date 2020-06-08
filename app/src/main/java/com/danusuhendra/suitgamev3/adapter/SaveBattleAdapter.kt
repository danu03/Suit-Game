package com.danusuhendra.suitgamev3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.danusuhendra.suitgamev3.R
import com.danusuhendra.suitgamev3.data.database.model.SaveBattle
import kotlinx.android.synthetic.main.item_save_battle.view.*

class SaveBattleAdapter(private val listSaveBattle : MutableList<SaveBattle>) : RecyclerView.Adapter<SaveBattleAdapter.ViewHolder>() {
    private lateinit var listener : OnClickListenerCallback<SaveBattle>
    fun setOnClickListener(listenerCallback : OnClickListenerCallback<SaveBattle>) {
        this.listener = listenerCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_save_battle, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listSaveBattle.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listSaveBattle[position], position)
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(saveBattle: SaveBattle, position: Int){
            itemView.tv_date.text = saveBattle.date
            itemView.tv_mode.text = saveBattle.mode
            itemView.tv_result.text = saveBattle.result
            itemView.tv_delete.setOnClickListener {
                listener.onDeleteClick(saveBattle, position)
            }
        }
    }

    interface OnClickListenerCallback<T> {
        fun onDeleteClick(data : T, position: Int)
    }

}