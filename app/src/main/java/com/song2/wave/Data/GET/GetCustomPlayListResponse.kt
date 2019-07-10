package com.song2.wave.Data.GET

import com.song2.wave.Data.model.CustomPlayListData

class GetCustomPlayListResponse(
    val status: Long,
    val success: Boolean,
    val message: String,
    val data: ArrayList<CustomPlayListData>

)