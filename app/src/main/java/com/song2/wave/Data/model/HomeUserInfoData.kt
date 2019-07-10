package com.song2.wave.Data.model

data class HomeUserInfoData (
    val nickname: String,
    val hitSongCount: Int?,
    val rateSongCount: Int?,
    val totalPoint: Int?,
    val auth: Boolean
)
