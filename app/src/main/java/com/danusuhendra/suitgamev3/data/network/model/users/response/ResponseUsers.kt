package com.danusuhendra.suitgamev3.data.network.model.users.response


import com.google.gson.annotations.SerializedName

data class ResponseUsers(
    @SerializedName("data")
    val dataUsers: DataUsers?,
    @SerializedName("success")
    val success: Boolean?
)