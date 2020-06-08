package com.danusuhendra.suitgamev3.data.network

import androidx.multidex.BuildConfig
import com.danusuhendra.suitgamev3.utils.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiResource  {
    fun created() : Retrofit{
        val logging = HttpLoggingInterceptor()
        logging.level =  HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(logging)
            .readTimeout(30,TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun apiServices() : ApiService{
        return created().create(ApiService::class.java)
    }
}