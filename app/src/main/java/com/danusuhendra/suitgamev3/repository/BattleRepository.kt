package com.danusuhendra.suitgamev3.repository

import android.util.Log
import com.danusuhendra.suitgamev3.data.network.ApiService
import com.danusuhendra.suitgamev3.data.network.model.battle.request.BodyBattle
import com.danusuhendra.suitgamev3.data.network.model.battle.response.DataGetBattle
import com.danusuhendra.suitgamev3.data.network.model.battle.response.ResponseGetBattle
import com.danusuhendra.suitgamev3.data.network.model.battle.response.ResponsePostBattle
import com.danusuhendra.suitgamev3.utils.ErrorParser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BattleRepository(private val apiService: ApiService) {

    suspend fun postBattle(battle: BodyBattle, token: String) {
        val response = apiService.postBattle(battle, token)
        if (response.isSuccessful) {

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