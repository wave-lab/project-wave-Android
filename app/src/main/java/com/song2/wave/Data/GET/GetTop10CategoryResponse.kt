package com.song2.wave.Data.GET

import com.song2.wave.Data.model.Top10CategoryData

data class GetTop10CategoryResponse(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: ArrayList<ArrayList<Top10CategoryData>>
)