package com.song2.wave.Data.model.Home

data class Top10ListData(
    var topRank : Int?,
    var topSongCoverImg : String?,
    var topSongName : String?,
    var topOriginArtistName : String,
    var topCoverArtistName : String,
    var topSongField : ArrayList<String?>
)