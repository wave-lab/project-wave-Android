package com.song2.wave.Util.Network

import com.song2.wave.Data.GET.GetHomeInfoResponse
import com.song2.wave.Data.GET.GetPlaylistResponse
import com.song2.wave.Data.GET.GetRecommendResponse
import com.song2.wave.Data.GET.GetTop10CategoryResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NetworkService {

    //home -> 개인정보
    @GET("pl/home/userInfo")
    fun getHomeInfoResponse(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") authorization: String
    ): Call<GetHomeInfoResponse>

    //home -> top10(장르,무드)
    @GET("pl/top10")
    fun getTop10CategoryResonse(
        @Header("Content-Type") content_type: String
    ): Call<GetTop10CategoryResponse>

    //home -> 추천곡
    @GET("pl/recommend")
    fun getRecommendResponse(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") authorization: String
    ) : Call<GetRecommendResponse>

    //평가 대기곡
    @GET("pl/rateReady")
    fun getRateReadyResponse(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") authorization: String
    ) : Call<GetPlaylistResponse>

    //내가 올린 곡(d-day)
    @GET("pl/upload")
    fun getUploadResponse(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") authorization: String
    ) : Call<GetPlaylistResponse>

    //적중 결과 곡
    //적중 결과 곡 status
    @GET("pl/hits")
    fun getHitsResponse(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") authorization: String
        //@Query("status") status : Int
    ) : Call<GetPlaylistResponse>


}