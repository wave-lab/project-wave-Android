package com.song2.wave.UI.Main.Search

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.song2.wave.Data.model.SongData
import com.song2.wave.R
import com.song2.wave.UI.Main.MainActivity
import com.song2.wave.UI.Main.Search.Adapter.SongSearchAdapter
import com.song2.wave.Util.Interface.OnBackPressedListener
import kotlinx.android.synthetic.main.fragment_artist_song.view.*


class SearchArtistFragment : android.support.v4.app.Fragment(), OnBackPressedListener{

    var TAG = "SearchArtistFragment"
    lateinit var songDataArr : ArrayList<SongData>
    var searchHomeFragment = SearchHomeFragment()
    lateinit var songSearchAdapter : SongSearchAdapter
    lateinit var songFieldData : ArrayList<String?>
    lateinit var requestManager : RequestManager

    override fun onBackPressed() {
        Log.e(TAG, "onBack()")
        // 한번 뒤로가기 버튼을 누르면 Listener 를 null, flag = 0 으로 해제
        MainActivity.mainActivity.setOnBackPressedListener(null, 0)
        // SearchHomeFragment 로 교체
        SearchFragment.searchFragment.replaceFragment(searchHomeFragment)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_artist_song,container,false)

        requestManager = Glide.with(this)
        songDataArr = ArrayList<SongData>()

        songSearchAdapter = SongSearchAdapter(songDataArr, requestManager)
        v.recycler_search_artist_frag_song.adapter = songSearchAdapter
        v.recycler_search_artist_frag_song.layoutManager = LinearLayoutManager(v.context)

        return v
    }

    // Fragment 호출 시 반드시 호출되는 오버라이드 메소드
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e(TAG, "onAttach()")
        (context as MainActivity).setOnBackPressedListener(this, 1)
    }



}