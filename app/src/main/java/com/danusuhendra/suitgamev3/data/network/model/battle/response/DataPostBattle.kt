package com.danusuhendra.suitgamev3.data.network.model.battle.response


import com.google.gson.annotations.SerializedName

data class DataPostBattle(
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("_id")
    val id: String?,
    @SerializedName("mode")
    val mode: String?,
    @SerializedName("result")
    val result: String?,
    @SerializedName("updatedAt")
    val updatedAt: String?
)