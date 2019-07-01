package com.song2.wave.UI.Main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.song2.wave.UI.Main.Home.HomeFragment
import com.song2.wave.UI.Main.Library.LibraryFragment
import com.song2.wave.UI.Main.Scoring.ScoringFragment
import com.song2.wave.UI.Main.Search.SearchFragment
import com.song2.wave.R
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var nowFrag: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addBottomTab()

    }

    fun addBottomTab() {

        ib_main_act_home_tab!!.setOnClickListener { callFragment("home") }
        ib_main_act_scoring_tab!!.setOnClickListener { callFragment("scoring") }
        ib_main_act_search_tab!!.setOnClickListener { callFragment("search") }
        ib_main_act_library_tab!!.setOnClickListener { callFragment("library") }

    }

    fun callFragment(frag: String) {

        when (frag) {
            "home" -> {
                nowFrag = HomeFragment()
            }
            "search" -> {
                nowFrag = SearchFragment()
            }
            "scoring" -> {
                nowFrag = ScoringFragment()
            }
            "library" -> {
                nowFrag = LibraryFragment()
            }
        }

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_fragment_container, nowFrag)
        transaction.commit()
    }
}
