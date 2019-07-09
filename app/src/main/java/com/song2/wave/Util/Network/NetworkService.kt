package com.song2.wave.Util.Network

import com.song2.wave.Util.Network.POST.PostResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface NetworkService {
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