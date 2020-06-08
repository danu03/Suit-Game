package com.danusuhendra.suitgamev3.data.network.model.users.response


import com.google.gson.annotations.SerializedName

data class DataUsers(
    @SerializedName("email")
    val email: String?,
    @SerializedName("_id")
    val id: String?,
    @SerializedName("token")
    val token: String?,
    @SerializedName("username")
    val username: String?,
    @SerializedName("photo")
    val photo: String?
)