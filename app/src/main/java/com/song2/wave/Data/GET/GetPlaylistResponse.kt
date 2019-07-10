package com.song2.wave.Data.GET

import com.song2.wave.Data.model.PlayListData

data class GetPlaylistResponse (
    val status: Long,
    val success: Boolean,
    val message: String,
    val data: PlayListData
)