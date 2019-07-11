package com.song2.wave.Data.GET

import com.song2.wave.Data.model.PlayListData
import com.song2.wave.Data.model.PlaySongData

data class GetSongDetailResponse (
    val status: Long,
    val success: Boolean,
    val message: String,
    val data: PlaySongData
)