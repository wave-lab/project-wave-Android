package com.song2.wave.Data.model

data class SongData (
        var _id : String?,
        var songUrl : String?,
        var songCoverImg : String?,
        var songName : String?,
        var originArtistName : String?,
        var coverArtistName : String?,
        var songField : ArrayList<String?>?
)