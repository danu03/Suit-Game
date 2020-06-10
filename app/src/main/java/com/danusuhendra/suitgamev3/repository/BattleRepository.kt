package com.danusuhendra.suitgamev3.repository

import com.danusuhendra.suitgamev3.data.network.ApiService
import com.danusuhendra.suitgamev3.data.network.model.battle.request.BodyBattle
import com.danusuhendra.suitgamev3.data.network.model.battle.response.DataGetBattle
import com.danusuhendra.suitgamev3.utils.ErrorParser

class BattleRepository(private val apiService: ApiService) {

    suspend fun postBattle(battle: BodyBattle, token: String, onError: (String?) -> Unit) {
        val response = apiService.postBattle(battle, token)
        if (response.isSuccessful) {

        } else {
            val errorBody = ErrorParser.errorBattle(response)
            onError(errorBody!!.errors)
        }
    }

    suspend fun getBattle(
        token: String,
        onSuccess: (MutableList<DataGetBattle>) -> Unit,
        onError: (String?) -> Unit
    ) {
        val response = apiService.getBattle(token)
        if (response.isSuccessful) {
            val listHistory = response.body()!!.data as MutableList<DataGetBattle>
            onSuccess(listHistory)
        } else {
            val errorBody = ErrorParser.errorBattle(response)
            onError(errorBody!!.errors)
        }
    }
}