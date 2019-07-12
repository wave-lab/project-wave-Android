package com.song2.wave.UI.Main.Search

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.song2.wave.R
import com.song2.wave.UI.Main.Search.Adapter.SearchArtistViewAdapter

class SearchCoverArtistFragment : Fragment() {
    lateinit var searchCoverArtistViewAdapter: SearchArtistViewAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search_origin_artist, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setRecyclerView()
    }
    private fun setRecyclerView() {
        //임시 데이터
       /* var dataList: ArrayList<SearchArtistData> = ArrayList()
        dataList.add(SearchArtistData("https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "개미는"))
        dataList.add(SearchArtistData("https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "뚠뚠"))
        dataList.add(SearchArtistData("https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "오늘도"))
        dataList.add(SearchArtistData("https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "뚠뚠"))
        dataList.add(SearchArtistData("https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "열심히"))
        dataList.add(SearchArtistData("https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "개미는"))
        dataList.add(SearchArtistData("https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "언제나"))
        dataList.add(SearchArtistData("https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "열심히"))
        dataList.add(SearchArtistData("https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "일을 하네"))
        dataList.add(SearchArtistData("https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "개미는 아무말도"))

        searchCoverArtistViewAdapter = SearchArtistViewAdapter(activity!!, dataList)
        rv_fragment_search_cover_artist_list.adapter =  searchCoverArtistViewAdapter
        rv_fragment_search_cover_artist_list.layoutManager = LinearLayoutManager(activity)*/
    }
}