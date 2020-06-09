package com.danusuhendra.suitgamev3.repository

import com.danusuhendra.suitgamev3.data.network.ApiService
import com.danusuhendra.suitgamev3.data.network.model.users.response.ResponseUsers
import com.danusuhendra.suitgamev3.utils.ErrorParser
import okhttp3.MultipartBody
import okhttp3.RequestBody

class ProfileRepository(private val apiService: ApiService) {

    suspend fun getDataProfile(
        token: String,
        onResponse: (ResponseUsers) -> Unit,
        onError: (String) -> Unit
    ) {
        val response = apiService.getUser(token)
        if (response.isSuccessful){
            onResponse(response.body()!!)
        }else{
            onError("Session Timeout, Please Login Again")
        }
    }

    suspend fun editProfile(token: String, username : RequestBody, email : RequestBody, file : MultipartBody.Part, onSuccess : (ResponseUsers) -> Unit, onError: (String) -> Unit, tokenExpired : (String) -> Unit) {
        val response = apiService.updateUser(token, username, email, file)
        if (response.isSuccessful) {
            onSuccess(response.body()!!)
        }else if(response.code() == 403){
            tokenExpired("Session Timeout, Please Login Again")
        }else{
            val errorBody = ErrorParser.errorLogin(response)
            onError(errorBody?.errors!!)
        }
    }
}