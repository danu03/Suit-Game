package com.danusuhendra.suitgamev3.repository

import com.danusuhendra.suitgamev3.data.network.ApiService
import com.danusuhendra.suitgamev3.data.network.model.users.response.ResponseAuthMe
import com.danusuhendra.suitgamev3.data.network.model.users.response.ResponseUsers
import com.danusuhendra.suitgamev3.utils.ErrorParser

class AuthRepository(private val apiService: ApiService) {

    suspend fun login(
        email: String,
        password: String,
        onSuccess: (ResponseUsers) -> Unit,
        onError: (String?) -> Unit
    ) {
        val response = apiService.login(email, password)
        if (response.isSuccessful) {
            onSuccess(response.body()!!)
        } else {
            val errorBody = ErrorParser.errorLogin(response)
            onError(errorBody?.errors)
        }
    }

    suspend fun register(
        email: String,
        password: String,
        username: String,
        onSuccess: (ResponseUsers) -> Unit,
        onError: (String?) -> Unit
    ) {
        val response = apiService.register(email, password, username)
        if (response.isSuccessful) {
            onSuccess(response.body()!!)
        } else {
            val errorBody = ErrorParser.errorLogin(response)
            onError(errorBody?.errors)
        }
    }

    suspend fun userAuth(
        token: String,
        isLogin: (Boolean) -> Unit,
        onSuccess: (ResponseAuthMe) -> Unit,
        onError: (String?) -> Unit
    ) {
        val response = apiService.userAuth(token)
        if (response.isSuccessful) {
            onSuccess(response.body()!!)
            isLogin(true)
        } else {
            val errorBody = ErrorParser.errorAuthMe(response)
            onError(errorBody?.errors)
            isLogin(false)
        }
    }
}