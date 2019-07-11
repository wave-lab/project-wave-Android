package com.song2.wave.Data.model.Scoring

data class TitleData (
    val genre: ArrayList<String?>,
    val mood: ArrayList<String?>,

    val id: String,

    val originTitle: String,
    val userIdx: Long,
    val coverArtistName: String,
    val streamCount: Long,
    val likeCount: Long,
    val artwork: String,
    val originArtistIdx: Long,
    val originArtistName: String,
    val enrollTime: String? = null,
    val songUrl: String,
    val songComment: String,
    val reportCount: Long,
    val rateScore: Long,
    val highlightTime: String,
    val songStatus: Long,
    val uploadDate: String,
    val deleteTime: String,
    val rateUserCount: Long
)