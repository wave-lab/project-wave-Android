package com.song2.wave.UI.Artist

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.song2.wave.R

class ArtistSongListFragment : Fragment() {
    lateinit var artist_song_view_adapter : ArtistSongListViewAdapter
    lateinit var songFieldData : ArrayList<String>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_artist_song_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setRecyclerView()
    }

    private fun setRecyclerView() {
        //임시데이터
        /*songFieldData = ArrayList<String>()
        songFieldData.add("분야1")
        var dataList: ArrayList<SongData> = ArrayList()
        dataList.add(SongData("http://cdn.news2day.co.kr/news-images/peg/news/201709/8r1YZtmQRWoSic7Q6fv6i3cnEuj2RP0sqJwwEWGa-wm-1505700400.jpg", "나는야","이선","이선", songFieldData))
        dataList.add(SongData("http://cdn.news2day.co.kr/news-images/peg/news/201709/8r1YZtmQRWoSic7Q6fv6i3cnEuj2RP0sqJwwEWGa-wm-1505700400.jpg", "나는야","이선","선이", songFieldData))
        dataList.add(SongData("http://cdn.news2day.co.kr/news-images/peg/news/201709/8r1YZtmQRWoSic7Q6fv6i3cnEuj2RP0sqJwwEWGa-wm-1505700400.jpg", "나는야","이선","이선", songFieldData))
        dataList.add(SongData("http://cdn.news2day.co.kr/news-images/peg/news/201709/8r1YZtmQRWoSic7Q6fv6i3cnEuj2RP0sqJwwEWGa-wm-1505700400.jpg", "나는야","이선","선이", songFieldData))
        dataList.add(SongData("http://cdn.news2day.co.kr/news-images/peg/news/201709/8r1YZtmQRWoSic7Q6fv6i3cnEuj2RP0sqJwwEWGa-wm-1505700400.jpg", "나는야","이선","이선", songFieldData))
        dataList.add(SongData("http://cdn.news2day.co.kr/news-images/peg/news/201709/8r1YZtmQRWoSic7Q6fv6i3cnEuj2RP0sqJwwEWGa-wm-1505700400.jpg", "나는야","이선","선이", songFieldData))
        dataList.add(SongData("http://cdn.news2day.co.kr/news-images/peg/news/201709/8r1YZtmQRWoSic7Q6fv6i3cnEuj2RP0sqJwwEWGa-wm-1505700400.jpg", "나는야","이선","이선", songFieldData))
        dataList.add(SongData("http://cdn.news2day.co.kr/news-images/peg/news/201709/8r1YZtmQRWoSic7Q6fv6i3cnEuj2RP0sqJwwEWGa-wm-1505700400.jpg", "나는야","이선","선이", songFieldData))
        dataList.add(SongData("http://cdn.news2day.co.kr/news-images/peg/news/201709/8r1YZtmQRWoSic7Q6fv6i3cnEuj2RP0sqJwwEWGa-wm-1505700400.jpg", "나는야","이선","이선", songFieldData))
        dataList.add(SongData("http://cdn.news2day.co.kr/news-images/peg/news/201709/8r1YZtmQRWoSic7Q6fv6i3cnEuj2RP0sqJwwEWGa-wm-1505700400.jpg", "나는야","이선","선이", songFieldData))
        dataList.add(SongData("http://cdn.news2day.co.kr/news-images/peg/news/201709/8r1YZtmQRWoSic7Q6fv6i3cnEuj2RP0sqJwwEWGa-wm-1505700400.jpg", "나는야","이선","이선", songFieldData))
        dataList.add(SongData("http://cdn.news2day.co.kr/news-images/peg/news/201709/8r1YZtmQRWoSic7Q6fv6i3cnEuj2RP0sqJwwEWGa-wm-1505700400.jpg", "나는야","이선","선이", songFieldData))
        artist_song_view_adapter = ArtistSongListViewAdapter(activity!!, dataList)
        rv_fragment_artist_song_list.adapter = artist_song_view_adapter
        rv_fragment_artist_song_list.layoutManager = LinearLayoutManager(activity)*/
    }
}