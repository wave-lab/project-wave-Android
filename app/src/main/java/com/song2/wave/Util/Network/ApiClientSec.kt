package com.song2.wave.Util.Network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClientSec {
    // BaseURL
    var client = OkHttpClient.Builder()
            .connectTimeout(3000, TimeUnit.SECONDS)
            .readTimeout(3000, TimeUnit.SECONDS).build()
    var BASE_URL = "http://15.164.70.24:3002"
    private var retrofit2: Retrofit? = null
    fun getRetrofit(): Retrofit {
        if (retrofit2 == null) {
            retrofit2 = Retrofit.Builder()
                .baseUrl(BASE_URL).client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit2!!
    }
}
