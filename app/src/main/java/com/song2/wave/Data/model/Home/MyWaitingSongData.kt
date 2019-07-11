package com.song2.wave.Data.model.Home

data class MyWaitingSongData(

    var songId : String,
    var songUrl : String,
    var songWaitingDay_mine : Int?,
    var songCoverImg_mine: String?,
    var songName_mine: String?,
    var originArtistName_mine: String,
    var coverArtistName : String
)