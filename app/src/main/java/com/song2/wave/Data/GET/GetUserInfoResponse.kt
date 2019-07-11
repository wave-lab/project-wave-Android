package com.song2.wave.Data.GET

import com.song2.wave.Data.model.UserInfoData

data class GetUserInfoResponse (
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: UserInfoData
)
