package com.song2.wave.Data.model

data class CustomPlayListData(
    val _id: String,
    val playlistName: String,
    val playlistComment: String,
    val userIdx: Long,
    val thumbnail: ArrayList<String?>
)