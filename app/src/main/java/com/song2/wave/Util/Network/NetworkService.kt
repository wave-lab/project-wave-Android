package com.song2.wave.Util.Network

import com.song2.wave.Data.GET.GetHomeInfoResponse
import com.song2.wave.Data.GET.GetPlaylistResponse
import com.song2.wave.Data.GET.GetRecommendResponse
import com.song2.wave.Data.GET.GetTop10CategoryResponse
import com.song2.wave.Util.Network.POST.PostResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

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

    //적중 성공 곡
    @GET("pl/hits")
    fun getHitsResponse(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") authorization: String
    ) : Call<GetPlaylistResponse>

    ////////////////////* POST *///////////////////////////

    // 회원가입
    @Multipart
    @POST("/api/signup")
    fun postSignup(
            @Part("email") email : RequestBody,
            @Part("password") password : RequestBody,
            @Part("nickname") nickname : RequestBody,
            @Part profileImg : MultipartBody.Part?,
            @Part("genre[0]") genre0 : RequestBody,
            @Part("genre[1]") genre1 : RequestBody,
            @Part("genre[2]") genre2 : RequestBody,
            @Part("mood[0]") mood0 : RequestBody,
            @Part("mood[1]") mood1 : RequestBody,
            @Part("originArtist[0]") originArtist : RequestBody
    ) : Call<PostResponse>

}