package com.song2.wave.Data.POST

import okhttp3.MultipartBody

data class PostSignUpData (
    var email : String,
    var password : String,
    var nickname : String,
    var profileImg : MultipartBody.Part?,
    var genre : ArrayList<String>,
    var mood : ArrayList<String>,
    var originArtist : ArrayList<Int>
)