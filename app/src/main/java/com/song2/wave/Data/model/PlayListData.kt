package com.song2.wave.Data.model

data class PlayListData(
    val songList: ArrayList<PlaySongData>,
    val _id: String,
    val playlistName: String,
    val playlistComment: String,
    val userIdx: Long
)