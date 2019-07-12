package com.song2.wave.UI.Main.Search

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.song2.wave.R
import com.song2.wave.UI.Main.Search.Adapter.SearchArtistViewAdapter

class SearchOriginArtistFragment : Fragment() {
    lateinit var searchOriginArtistViewAdapter: SearchArtistViewAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search_origin_artist, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

            // setRecyclerView()
    }
    /*private fun setRecyclerView() {
        //임시 데이터
        var dataList: ArrayList<SearchArtistData> = ArrayList()
        dataList.add(
            SearchArtistData(
                "https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E",
                "이것은"
            )
        )
        dataList.add(
            SearchArtistData(
                "https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E",
                "Origin"
            )
        )
        dataList.add(
            SearchArtistData(
                "https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E",
                "입니다"
            )
        )
        dataList.add(
            SearchArtistData(
                "https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E",
                "Artist"
            )
        )
        dataList.add(
            SearchArtistData(
                "https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E",
                "이선이"
            )
        )
        dataList.add(
            SearchArtistData(
                "https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E",
                "부릅니다"
            )
        )
        dataList.add(
            SearchArtistData(
                "https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E",
                "소주 한잔"
            )
        )

        searchOriginArtistViewAdapter = SearchArtistViewAdapter(activity!!, dataList)
        rv_fragment_search_origin_artist_list.adapter = searchOriginArtistViewAdapter
        rv_fragment_search_origin_artist_list.layoutManager = LinearLayoutManager(activity)
    }*/
}