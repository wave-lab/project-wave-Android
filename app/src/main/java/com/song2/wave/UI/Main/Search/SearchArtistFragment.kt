package com.song2.wave.UI.Main.Search

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.song2.wave.R
import com.song2.wave.UI.Main.MainActivity
import com.song2.wave.Util.Interface.OnBackPressedListener


class SearchArtistFragment : android.support.v4.app.Fragment(), OnBackPressedListener{

    var TAG = "SearchArtistFragment"
    var searchHomeFragment = SearchHomeFragment()

    override fun onBackPressed() {
        Log.e(TAG, "onBack()")
        // 한번 뒤로가기 버튼을 누르면 Listener 를 null, flag = 0 으로 해제
        MainActivity.mainActivity.setOnBackPressedListener(null, 0)
        // SearchHomeFragment 로 교체
        SearchFragment.searchFragment.replaceFragment(searchHomeFragment)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_artist_song,container,false)

        return v
    }

    // Fragment 호출 시 반드시 호출되는 오버라이드 메소드
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e(TAG, "onAttach()")
        (context as MainActivity).setOnBackPressedListener(this, 1)
    }

}