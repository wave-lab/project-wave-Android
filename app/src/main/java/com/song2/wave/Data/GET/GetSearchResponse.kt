package com.song2.wave.Data.GET

import com.song2.wave.Data.model.SearchResultData

data class GetSearchResponse (
    val status: Long,
    val success: Boolean,
    val message: String,
    val data: SearchResultData?
)