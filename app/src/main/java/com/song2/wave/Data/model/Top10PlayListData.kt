package com.song2.wave.Data.model

data class Top10PlayListData(
    val _id: String,
    val songList: ArrayList<PlaySongData>?,
    val playlistName: String,
    val playlistComment: String
)