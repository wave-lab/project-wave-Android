package com.song2.wave.Data.model.Search

data class SongData (
        var songCoverImg : String?,
        var songName : String?,
        var originArtistName : String,
        var coverArtistName : String,
        var songField : ArrayList<String>
)