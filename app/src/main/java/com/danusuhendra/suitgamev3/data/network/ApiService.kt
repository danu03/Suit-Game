package com.danusuhendra.suitgamev3.data.network

import com.danusuhendra.suitgamev3.data.network.model.battle.request.BodyBattle
import com.danusuhendra.suitgamev3.data.network.model.battle.response.ResponseGetBattle
import com.danusuhendra.suitgamev3.data.network.model.battle.response.ResponsePostBattle
import com.danusuhendra.suitgamev3.data.network.model.users.request.BodyLogin
import com.danusuhendra.suitgamev3.data.network.model.users.request.BodyRegister
import com.danusuhendra.suitgamev3.data.network.model.users.response.ResponseAuthMe
import com.danusuhendra.suitgamev3.data.network.model.users.response.ResponseUsers
import com.danusuhendra.suitgamev3.utils.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST(REGISTER)
    suspend fun register(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("username") username: String
    ): Response<ResponseUsers>

    @FormUrlEncoded
    @POST(LOGIN)
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<ResponseUsers>

    @GET(USER_AUTH)
    suspend fun userAuth(@Header("Authorization") token: String): Response<ResponseAuthMe>

    @GET(USER_UPDATE)
    fun getUser(@Header("Authorization") token: String): Call<ResponseUsers>

    @Multipart
    @PUT(USER_UPDATE)
    fun updateUser(
        @Header("Authorization") token: String,
        @Part("username") username: RequestBody,
        @Part("email") email: RequestBody,
        @Part file: MultipartBody.Part
    ): Call<ResponseUsers>

    @POST(BATTLE)
    suspend fun postBattle(
        @Body bodyBattle: BodyBattle,
        @Header("Authorization") token: String
    ): Response<ResponsePostBattle>

    @GET(BATTLE)
    suspend fun getBattle(@Header("Authorization") token: String): Response<ResponseGetBattle>
}