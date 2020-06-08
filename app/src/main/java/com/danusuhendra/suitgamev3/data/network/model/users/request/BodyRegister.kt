package com.danusuhendra.suitgamev3.data.network.model.users.request


import com.google.gson.annotations.SerializedName

data class BodyRegister(
    @SerializedName("email")
    val email: String?,
    @SerializedName("password")
    val password: String?,
    @SerializedName("username")
    val username: String?
)