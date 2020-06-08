package com.danusuhendra.suitgamev3.data.network.model.users.response


import com.google.gson.annotations.SerializedName

data class ResponseAuthMe(
    @SerializedName("data")
    val `data`: Data?,
    @SerializedName("success")
    val success: Boolean?,
    @SerializedName("errors")
    val errors: String?
)