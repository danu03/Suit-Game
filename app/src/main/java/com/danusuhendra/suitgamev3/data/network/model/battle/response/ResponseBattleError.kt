package com.danusuhendra.suitgamev3.data.network.model.battle.response


import com.google.gson.annotations.SerializedName

data class ResponseBattleError(
    @SerializedName("errors")
    val errors: String,
    @SerializedName("status")
    val status: Boolean
)