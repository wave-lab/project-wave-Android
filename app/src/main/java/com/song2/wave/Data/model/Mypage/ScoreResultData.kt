package com.song2.wave.Data.model.Mypage

data class ScoreResultData(
    var songScore: Int?,
    var songCoverImg : String?,
    var songName : String?,
    var songOriginArtist : String?,
    var songFiled : ArrayList<String?>
)