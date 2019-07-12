package com.song2.wave.Data.GET

import com.song2.wave.Data.model.Top10PlayListData

data class GetTop10PlaylistResponse(
    var status : Int?,
    var success: Boolean,
    var data : Top10PlayListData
)