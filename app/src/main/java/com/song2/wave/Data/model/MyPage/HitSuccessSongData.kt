package com.song2.wave.Data.model.MyPage

data class HitSuccessSongData (
    var songCoverImg : String?,
    var songName : String?,
    var originArtistName : String,
    var coverArtistName : String,
    var songField : ArrayList<String>,
    var avgScore : Double,
    var myScore : Int

)