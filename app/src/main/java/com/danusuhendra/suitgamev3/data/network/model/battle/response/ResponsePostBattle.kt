package com.danusuhendra.suitgamev3.data.network.model.battle.response


import com.google.gson.annotations.SerializedName

data class ResponsePostBattle(
    @SerializedName("data")
    val dataPostBattle: DataPostBattle?,
    @SerializedName("success")
    val success: Boolean?
)