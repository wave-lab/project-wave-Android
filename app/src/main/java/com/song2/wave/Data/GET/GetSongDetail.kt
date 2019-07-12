package com.song2.wave.Data.GET

data class GetSongDetail (

    var genre : ArrayList<String>,
    var mood : ArrayList<String>,
    var _id : String,
    var originTitle : String,
    var userIdx : Int,
    var coverArtistName : String,
    var streamCount : Int,
    var likeCount : Int,
    var artwork : String,
    var originArtistIdx : Int,
    var originArtistName : String,
    var enrollTime : String,
    var songUrl : String,
    var songComment : String,
    var reportCount : Int,
    var rateScore : Int,
    var highlightTime : String,
    var songStatus : Int,
    var uploadDate : String,
    var deleteTime : String,
    var rateUserCount : Int
)