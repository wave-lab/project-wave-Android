package com.song2.wave.UI.Main.Home.Top10.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.song2.wave.UI.Main.Home.Top10.*

class FragmentViewPagerAdapter(fm: FragmentManager, private val num_fragment: Int): FragmentStatePagerAdapter(fm){
    override fun getItem(p0: Int): Fragment? {
        return when(p0){
            0 -> Idx0Fragment()
            1 -> Idx1Fragment()
            2 -> Idx2Fragment()
            3 -> Idx3Fragment()
            4 -> Idx4Fragment()
            5 -> Idx5Fragment()
            6 -> Idx6Fragment()
            7 -> Idx7Fragment()
            else -> null
        }
    }

    override fun getCount(): Int{
        return num_fragment
    }
}