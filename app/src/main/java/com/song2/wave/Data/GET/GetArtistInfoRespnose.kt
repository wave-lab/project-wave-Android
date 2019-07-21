package com.song2.wave.Data.GET

import com.song2.wave.Data.model.ArtistInfoData

data class GetArtistInfoResponse(
    val status: Long,
    val success: Boolean,
    val message: String,
    val data: ArtistInfoData
)