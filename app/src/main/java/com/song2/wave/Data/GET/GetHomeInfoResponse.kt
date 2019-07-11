package com.song2.wave.Data.GET

import com.song2.wave.Data.model.HomeUserInfoData

data class GetHomeInfoResponse (
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: HomeUserInfoData
)