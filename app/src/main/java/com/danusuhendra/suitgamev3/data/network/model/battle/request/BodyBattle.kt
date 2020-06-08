package com.danusuhendra.suitgamev3.data.network.model.battle.request


import com.google.gson.annotations.SerializedName

data class BodyBattle(
    @SerializedName("mode")
    val mode: String?,
    @SerializedName("result")
    val result: String?
)