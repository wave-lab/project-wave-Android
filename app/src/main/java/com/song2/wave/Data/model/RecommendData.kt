package com.song2.wave.Data.model

data class RecommendData(

    val genre: List<String>,
    val mood: List<String>,

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
    val songURL: String,
    val songComment: String,
    val reportCount: Int,
    val rateScore: Int,
    val highlightTime: String,
    val songStatus: Int,
    val uploadDate: String,
    val deleteTime: String,
    val rateUserCount: Int

)