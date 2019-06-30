package com.song2.wave.UI.Main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.song2.wave.UI.Main.Home.HomeFragment
import com.song2.wave.UI.Main.Library.LibraryFragment
import com.song2.wave.UI.Main.Scoring.ScoringFragment
import com.song2.wave.UI.Main.Search.SearchFragment
import com.song2.wave.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addBottomTab()
    }

    fun addBottomTab(){
        val homeFragment = HomeFragment()
        val searchFragment = SearchFragment()
        val scoringFragment = ScoringFragment()
        val libraryFragment = LibraryFragment()
    }

}
