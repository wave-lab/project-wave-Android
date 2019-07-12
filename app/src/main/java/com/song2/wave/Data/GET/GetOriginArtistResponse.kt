package com.song2.wave.Data.GET

import com.song2.wave.Data.model.SignUp.OriginArtistData

data class GetOriginArtistResponse (
    val status: Long,
    val success: Boolean,
    val message: String,
    val data: ArrayList<OriginArtistData>
)