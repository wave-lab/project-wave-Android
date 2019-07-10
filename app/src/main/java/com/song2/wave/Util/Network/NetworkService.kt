package com.song2.wave.Util.Network

import com.google.gson.JsonObject
import com.song2.wave.Data.GET.GetHomeInfoResponse
import com.song2.wave.Data.GET.GetTop10Response
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface NetworkService {

    //home -> 개인정보
    @GET("pl/home/userInfo")
    fun getHomeInfoResponse(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") authorization: String
    ): Call<GetHomeInfoResponse>

    //home -> top10(장르,무드)
    @GET("")
    fun getTop10Resonse(
        @Header("Content-Type") content_type: String
    ): Call<GetTop10Response>

}