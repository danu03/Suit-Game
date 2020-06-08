package com.danusuhendra.suitgamev3.data.network.model.users.response

import com.google.gson.annotations.SerializedName

data class ResponseError(
    @SerializedName("errors")
    var errors: String?,
    @SerializedName("success")
    var success: Boolean?
)