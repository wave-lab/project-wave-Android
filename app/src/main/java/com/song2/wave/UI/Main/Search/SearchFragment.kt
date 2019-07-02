package com.song2.wave.UI.Main.Search

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.song2.wave.Data.model.Search.CoverArtistData
import com.song2.wave.Data.model.Search.SongData
import com.song2.wave.R
import com.song2.wave.UI.Main.Search.Adapter.CoverArtistSearchAdapter
import com.song2.wave.UI.Main.Search.Adapter.SongSearchAdapter
import kotlinx.android.synthetic.main.fragment_search.view.*

class SearchFragment : Fragment(){

    lateinit var songDataArr : ArrayList<SongData>
    lateinit var coverArtistDataArr : ArrayList<CoverArtistData>
    lateinit var songFieldData : ArrayList<String>

    lateinit var songSearchAdapter : SongSearchAdapter
    lateinit var coverArtistAdapter: CoverArtistSearchAdapter

    lateinit var requestManager : RequestManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v : View = inflater.inflate(R.layout.fragment_search, container,false)

        songDataArr = ArrayList<SongData>()
        coverArtistDataArr = ArrayList<CoverArtistData>()
        requestManager = Glide.with(this)

        insertExampleData()

        songSearchAdapter = SongSearchAdapter(songDataArr, requestManager)
        v.recycler_search_frag_song.adapter = songSearchAdapter
        v.recycler_search_frag_song.layoutManager = LinearLayoutManager(v.context)

        coverArtistAdapter = CoverArtistSearchAdapter(coverArtistDataArr, requestManager)
        v.recycler_search_frag_artist.adapter = coverArtistAdapter
        v.recycler_search_frag_artist.layoutManager = LinearLayoutManager(v.context, LinearLayoutManager.HORIZONTAL, false)

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    fun insertExampleData(){
        songFieldData = ArrayList<String>()
        songFieldData.add("분야1")
        songDataArr.add(SongData("https://t1.daumcdn.net/cfile/tistory/2442394558BBBD1934", "좋은날", "아이유(IU)", "송제민", songFieldData))
        songDataArr.add(SongData("http://cdn.news2day.co.kr/news-images/peg/news/201709/8r1YZtmQRWoSic7Q6fv6i3cnEuj2RP0sqJwwEWGa-wm-1505700400.jpg", "가을아침", "아이유(IU)", "송제민", songFieldData))
        songDataArr.add(SongData("data:image/jpeg;base64,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", "Zeze", "아이유(IU)", "송제민", songFieldData))
        songDataArr.add(SongData("https://cphoto.asiae.co.kr/listimglink/1/2014051608371615808_1.jpg", "꽃갈피", "아이유(IU)", "송제민", songFieldData))
        songDataArr.add(SongData("https://pgnqdrjultom1827145.cdn.ntruss.com/img/f8/b9/f8b99005f6cc026302a55f0cba36c19ecbf1f2109f36639664a1c4217bbb41cd_v1.jpg", "무릎", "아이유(IU)", "송제민", songFieldData))


        coverArtistDataArr.add(CoverArtistData("https://t1.daumcdn.net/cfile/tistory/2641FF4C5900DDDE1E", "박보영"))
        coverArtistDataArr.add(CoverArtistData("https://t1.daumcdn.net/cfile/tistory/2641FF4C5900DDDE1E", "박보영"))
        coverArtistDataArr.add(CoverArtistData("https://t1.daumcdn.net/cfile/tistory/2641FF4C5900DDDE1E", "박보영"))
        coverArtistDataArr.add(CoverArtistData("https://t1.daumcdn.net/cfile/tistory/2641FF4C5900DDDE1E", "박보영"))
        coverArtistDataArr.add(CoverArtistData("https://t1.daumcdn.net/cfile/tistory/2641FF4C5900DDDE1E", "박보영"))
        coverArtistDataArr.add(CoverArtistData("https://t1.daumcdn.net/cfile/tistory/2641FF4C5900DDDE1E", "박보영"))
        coverArtistDataArr.add(CoverArtistData("https://t1.daumcdn.net/cfile/tistory/2641FF4C5900DDDE1E", "박보영"))
        coverArtistDataArr.add(CoverArtistData("https://t1.daumcdn.net/cfile/tistory/2641FF4C5900DDDE1E", "박보영"))

    }
}