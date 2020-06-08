package com.danusuhendra.suitgamev3.data.network.model.battle.response


import com.google.gson.annotations.SerializedName

data class ResponseGetBattle(
    @SerializedName("data")
    val `data`: List<DataGetBattle>?,
    @SerializedName("success")
    val success: Boolean?
)