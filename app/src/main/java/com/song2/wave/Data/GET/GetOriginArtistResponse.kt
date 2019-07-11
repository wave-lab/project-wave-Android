package com.song2.wave.Data.GET

data class GetOriginArtistResponse (
    val status: Long,
    val success: Boolean,
    val message: String,
    val data: ArrayList<OriginArtistData>
)