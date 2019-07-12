package com.song2.wave

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.song2.wave.Data.model.SongData
import kotlinx.android.synthetic.main.activity_artist_song_list.*

class ArtistSongListActivity : AppCompatActivity() {
    lateinit var artist_song_list_view_adapter : ArtistSongListViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist_song_list)

        setRecyclerView()
    }

    fun setRecyclerView(){
        //임시데이터
        var songFieldData = ArrayList<String>()
        songFieldData.add("힙합")
        songFieldData.add("R&B")
        songFieldData.add("재즈")
//
//        var dataList: ArrayList<SongData> = ArrayList()11
//        dataList.add(SongData("1","df", "http://cdn.news2day.co.kr/news-images/peg/news/201709/8r1YZtmQRWoSic7Q6fv6i3cnEuj2RP0sqJwwEWGa-wm-1505700400.jpg", "나는야","이선","이선",  songFieldData!!))
//        dataList.add(SongData("1","df", "http://cdn.news2day.co.kr/news-images/peg/news/201709/8r1YZtmQRWoSic7Q6fv6i3cnEuj2RP0sqJwwEWGa-wm-1505700400.jpg", "나는야","이선","이선",  songFieldData))
//        dataList.add(SongData("1","df", "http://cdn.news2day.co.kr/news-images/peg/news/201709/8r1YZtmQRWoSic7Q6fv6i3cnEuj2RP0sqJwwEWGa-wm-1505700400.jpg", "나는야","이선","이선",  songFieldData))
//        dataList.add(SongData("1","df", "http://cdn.news2day.co.kr/news-images/peg/news/201709/8r1YZtmQRWoSic7Q6fv6i3cnEuj2RP0sqJwwEWGa-wm-1505700400.jpg", "나는야","이선","이선",  songFieldData))
//        dataList.add(SongData("1","df", "http://cdn.news2day.co.kr/news-images/peg/news/201709/8r1YZtmQRWoSic7Q6fv6i3cnEuj2RP0sqJwwEWGa-wm-1505700400.jpg", "나는야","이선","이선",  songFieldData))
//        dataList.add(SongData("1","df", "http://cdn.news2day.co.kr/news-images/peg/news/201709/8r1YZtmQRWoSic7Q6fv6i3cnEuj2RP0sqJwwEWGa-wm-1505700400.jpg", "나는야","이선","이선",  songFieldData))
//        dataList.add(SongData("1","df", "http://cdn.news2day.co.kr/news-images/peg/news/201709/8r1YZtmQRWoSic7Q6fv6i3cnEuj2RP0sqJwwEWGa-wm-1505700400.jpg", "나는야","이선","이선",  songFieldData))
//        dataList.add(SongData("1","df", "http://cdn.news2day.co.kr/news-images/peg/news/201709/8r1YZtmQRWoSic7Q6fv6i3cnEuj2RP0sqJwwEWGa-wm-1505700400.jpg", "나는야","이선","이선",  songFieldData))
//        dataList.add(SongData("1","df", "http://cdn.news2day.co.kr/news-images/peg/news/201709/8r1YZtmQRWoSic7Q6fv6i3cnEuj2RP0sqJwwEWGa-wm-1505700400.jpg", "나는야","이선","이선",  songFieldData))
//        dataList.add(SongData("1","df", "http://cdn.news2day.co.kr/news-images/peg/news/201709/8r1YZtmQRWoSic7Q6fv6i3cnEuj2RP0sqJwwEWGa-wm-1505700400.jpg", "나는야","이선","이선",  songFieldData))
//        dataList.add(SongData("1","df", "http://cdn.news2day.co.kr/news-images/peg/news/201709/8r1YZtmQRWoSic7Q6fv6i3cnEuj2RP0sqJwwEWGa-wm-1505700400.jpg", "나는야","이선","이선",  songFieldData))
//        dataList.add(SongData("1","df", "http://cdn.news2day.co.kr/news-images/peg/news/201709/8r1YZtmQRWoSic7Q6fv6i3cnEuj2RP0sqJwwEWGa-wm-1505700400.jpg", "나는야","이선","이선",  songFieldData))
//        dataList.add(SongData("1","df", "http://cdn.news2day.co.kr/news-images/peg/news/201709/8r1YZtmQRWoSic7Q6fv6i3cnEuj2RP0sqJwwEWGa-wm-1505700400.jpg", "나는야","이선","이선",  songFieldData))
//        dataList.add(SongData("1","df", "http://cdn.news2day.co.kr/news-images/peg/news/201709/8r1YZtmQRWoSic7Q6fv6i3cnEuj2RP0sqJwwEWGa-wm-1505700400.jpg", "나는야","이선","이선",  songFieldData))
//        dataList.add(SongData("1","df", "http://cdn.news2day.co.kr/news-images/peg/news/201709/8r1YZtmQRWoSic7Q6fv6i3cnEuj2RP0sqJwwEWGa-wm-1505700400.jpg", "나는야","이선","이선",  songFieldData))
//        dataList.add(SongData("1","df", "http://cdn.news2day.co.kr/news-images/peg/news/201709/8r1YZtmQRWoSic7Q6fv6i3cnEuj2RP0sqJwwEWGa-wm-1505700400.jpg", "나는야","이선","이선",  songFieldData))
//        dataList.add(SongData("1","df", "http://cdn.news2day.co.kr/news-images/peg/news/201709/8r1YZtmQRWoSic7Q6fv6i3cnEuj2RP0sqJwwEWGa-wm-1505700400.jpg", "나는야","이선","이선",  songFieldData))
//
//
//        artist_song_list_view_adapter = ArtistSongListViewAdapter(this!!, dataList)
//        rv_activity_artist_song_list.adapter = artist_song_list_view_adapter
//        rv_activity_artist_song_list.layoutManager = LinearLayoutManager(this)
    }
}
