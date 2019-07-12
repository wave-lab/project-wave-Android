package com.song2.wave.Util.Network

import com.google.gson.JsonObject
import com.song2.wave.Data.GET.GetHomeInfoResponse
import com.song2.wave.Data.GET.GetPlaylistResponse
import com.song2.wave.Data.GET.GetRecommendResponse
import com.song2.wave.Data.GET.GetTop10CategoryResponse
import com.song2.wave.Util.Network.POST.PostResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*
import com.song2.wave.Data.GET.*
import com.song2.wave.Data.GET.GetSearchResponse
import com.song2.wave.Data.POST.PostEmailData
import com.song2.wave.Data.POST.PostSignUpData
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NetworkService {

    //home -> 개인정보
    @GET("/pl/home/userInfo")
    fun getHomeInfoResponse(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") authorization: String
    ): Call<GetHomeInfoResponse>

    //home -> top10(장르,무드)
    @GET("/pl/top10")
    fun getTop10CategoryResonse(
        @Header("Content-Type") content_type: String
    ): Call<GetTop10CategoryResponse>

    //home -> 추천곡
    @GET("/pl/recommend")
    fun getRecommendResponse(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") authorization: String
    ): Call<GetRecommendResponse>

    //평가 대기곡
    @GET("/pl/rateReady")
    fun getRateReadyResponse(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") authorization: String
    ): Call<GetPlaylistResponse>

    //내가 올린 곡(d-day)
    //내가 올린 곡 mypage
    @GET("/pl/upload")
    fun getUploadResponse(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") authorization: String,
        @Query("status") status: String?
    ): Call<GetPlaylistResponse>

    // 곡 상세정보 조회
    @GET("/core/songs/{songIdx}")
    fun getSongDetailResonse(
        @Header("Authorization") authorization: String,
        @Path("songIdx") songIdx: String?
    ): Call<GetSongDetailResponse>

    //적중 결과 곡
    //적중 결과 곡 status
    @GET("/pl/hits")
    fun getHitsResponse(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") authorization: String,
        @Query("status") status: String?
    ): Call<GetPlaylistResponse>

    //마이페이지 - 일반유저
    @GET("/core/users")
    fun getUserInfoResponse(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") authorization: String
    ): Call<GetUserInfoResponse>

    @GET("/pl/likes")
    fun getLikesPlaylistResponse(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") authorization: String
    ): Call<GetPlaylistResponse>

    @GET("/pl/rated")
    fun getRatedResponse(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") authorization: String,
        @Query("status") status: String?
    ): Call<GetRecommendResponse>

    @GET("/pl/custom")
    fun getCustomPlaylistResponse(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") authorization: String
    ): Call<GetCustomPlayListResponse>

    @GET("/core/search")
    fun getSearchResponse(
        @Header("Content-Type") content_type: String,
        @Query("originArtistName") originartistname: String?,
        @Query("artistName") artistname: String?,
        @Query("originTitle") origintitle: String?

    ): Call<GetSearchResponse>


    @GET("/core/users/emailCheck")
    fun getEmailCheckResponse(
        @Query("email") email: String?
    ): Call<GetEmailCheckResponse>

    @GET("/core/users/nicknameCheck")
    fun getNicknameCheckResponse(
        @Query("nickname") nickname: String?
    ): Call<GetNicknameCheckResponse>

    @GET("/pl/{playlistIdx}")
    fun getTop10PlaylistResponse(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") authorization: String,
        @Path("playlistIdx") playlistIdx: String?
    ): Call<GetTop10PlaylistResponse>

    //** ds 작업 **//
    @GET("/core/originArtist")
    fun getOriginArtistResponse(
        @Header("Content-Type") content_type: String
    ): Call<GetOriginArtistResponse>

/*
    @GET("/core/users/{userIdx}")
    fun getArtistInfoRespnose(
        @Header("Content-Type") content_type: String
        @Path("userIdx") userIdx
    ) : Call<GetArtistInfoRespnose>
*/

    ////////////////////* POST *///////////////////////////

    // 회원가입
    @Multipart
    @POST("/signup")
    fun postSignupResponse(
            @Part("email") email : RequestBody,
            @Part("password") password : RequestBody,
            @Part("nickname") nickname : RequestBody,
            @Part profileImg : MultipartBody.Part?,
            @Part("genre") genre : ArrayList<RequestBody?>,
            @Part("mood") mood : ArrayList<RequestBody?>,
            @Part("originArtist") originArtist : ArrayList<RequestBody?>
    ) : Call<PostResponse>

    @Multipart
    @POST("/songs")
    fun postSongUploadResponse(
        @Part("originTitle") originTitle : RequestBody,
        @Part artwork : MultipartBody.Part?,
        @Part("originArtistName") originArtistName : RequestBody,
        @Part profileImg : MultipartBody.Part?,
        @Part("genre") genre : ArrayList<RequestBody?>,
        @Part("mood") mood : ArrayList<RequestBody?>,
        @Part("songComment") songComment : RequestBody,
        @Part("highlightTime") highlightTime : RequestBody
    ) : Call<PostResponse>

    // 이메일 중복 확인
    @POST("/core/users/emailCheck")
    fun postEmailCheckResponse(
        @Header("Authorization") authorization: String,
        @Body() body: JsonObject
    ): Call<PostResponse>

}