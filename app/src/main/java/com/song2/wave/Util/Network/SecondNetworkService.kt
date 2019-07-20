package com.song2.wave.Util.Network

import com.song2.wave.Data.GET.GetNicknameCheckResponse
import com.song2.wave.Data.GET.GetSearchResponse
import com.song2.wave.Util.Network.POST.PostResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface SecondNetworkService {

    // 회원가입
    @Multipart
    @POST("/signup")
    fun postSignupsResponse(
            @Part("email") email : RequestBody,
            @Part("password") password : RequestBody,
            @Part("nickname") nickname : RequestBody,
            @Part profileImg : MultipartBody.Part?,
            @Part("genre") genre : ArrayList<RequestBody>,
            @Part("mood") mood : ArrayList<RequestBody>,
            @Part("originArtist") originArtist : ArrayList<RequestBody>
    ) : Call<PostResponse>

    // 새로운 방 생성
    @Multipart
    @POST("boot/rest/posts/room")
    fun postRoomTest(
            @Part("roomCreaterID") roomCreaterID : RequestBody,
            @Part("roomName") roomName : RequestBody,
            @Part("roomStartDate") roomStartDate : RequestBody,
            @Part("roomEndDate") roomEndDate: RequestBody,
            @Part("roomTypeID") roomTypeID : RequestBody,
            @Part image : MultipartBody.Part?
    ) : Call<PostResponse>

    @GET("/search")
    fun getSearch(
    ) : Call<GetSearchResponse>

}