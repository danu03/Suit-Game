package com.danusuhendra.suitgamev3.utils

import com.danusuhendra.suitgamev3.data.network.ApiResource
import com.danusuhendra.suitgamev3.data.network.model.battle.response.ResponseBattleError
import com.danusuhendra.suitgamev3.data.network.model.users.response.ResponseAuthMe
import com.danusuhendra.suitgamev3.data.network.model.users.response.ResponseError
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Response
import java.io.IOException

object ErrorParser {
    fun errorLogin(response : Response<*>) : ResponseError? {
        val converter : Converter<ResponseBody, ResponseError> = ApiResource.created().responseBodyConverter(ResponseError::class.java, arrayOfNulls<Annotation>(0))
        val error : ResponseError?
        error = try {
            converter.convert(response.errorBody()!!)
        }catch (e : IOException) {
            return null
        }
        return error
    }

    fun errorAuthMe(response: Response<*>) : ResponseAuthMe? {
        val converter : Converter<ResponseBody, ResponseAuthMe> = ApiResource.created().responseBodyConverter(ResponseAuthMe::class.java, arrayOfNulls<Annotation>(0))
        val error : ResponseAuthMe?
        error = try {
            converter.convert(response.errorBody()!!)
        }catch (e : IOException) {
            return null
        }
        return error
    }

    fun errorBattle(response: Response<*>) : ResponseBattleError? {
        val converter : Converter<ResponseBody, ResponseBattleError> = ApiResource.created().responseBodyConverter(ResponseBattleError::class.java, arrayOfNulls<Annotation>(0))
        val error : ResponseBattleError?
        error = try {
            converter.convert(response.errorBody()!!)
        }catch (e : IOException) {
            return null
        }
        return error
    }
}