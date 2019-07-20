package com.song2.wave.Data.model

data class PlaySongData(

    val genre: ArrayList<String?>,
    val mood: ArrayList<String?>,

    val _id: String,
    val originTitle: String,
    val userIdx: Int,
    val coverArtistName: String,
    val streamCount: Int,
    val likeCount: Int,
    val artwork: String,
    val originArtistIdx: Int,
    val originArtistName: String,
    val enrollTime: String,
    val songUrl: String,
    val songUri: String,
    val songComment: String,
    val reportCount: Int,
    val rateScore: Int,
    val highlightTime: String,
    val songStatus: Int,
    val uploadDate: String,
    val deleteTime: String,
    val rateUserCount: Int

)