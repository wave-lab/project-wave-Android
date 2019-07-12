package com.song2.wave.Data.GET

import com.song2.wave.Data.model.PlaySongData

data class GetRecommendResponse(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: ArrayList<PlaySongData>
)