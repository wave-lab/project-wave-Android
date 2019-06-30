package com.song2.wave

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.song2.wave.Fragment.HomeFragment
import com.song2.wave.Fragment.LibraryFragment
import com.song2.wave.Fragment.ScoringFragment
import com.song2.wave.Fragment.SearchFragment

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
