package com.song2.wave.Data.model

import com.song2.wave.Data.model.Scoring.TitleData

data class SearchResultData(
    var originArtistName : ArrayList<OriginArtistData>,
    var originTitle : ArrayList<TitleData>,
    var artistName : ArrayList<SearchCoverArtistData>
)