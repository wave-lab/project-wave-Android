package com.song2.wave.Data.GET

data class GetEmailCheckResponse (
        val status: Long,
        val success: Boolean,
        val message: String,
        val data: String
)