package com.song2.wave.UI.Main.Scoring.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.song2.wave.UI.Main.Scoring.ScoringCompletedFragment
import com.song2.wave.UI.Main.Scoring.ScoringWaitingFragment

class FragmentScoringPagerAdapter(fm: FragmentManager, private val num_fragment: Int): FragmentStatePagerAdapter(fm){

    override fun getItem(p0: Int): Fragment? {
        return when(p0){
            0 -> ScoringWaitingFragment()
            1 -> ScoringCompletedFragment()
            else -> null
        }
    }

    override fun getCount(): Int{
        return num_fragment
    }
}