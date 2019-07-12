package com.song2.wave.UI.Main.Home.Top10.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.song2.wave.UI.Main.Home.Top10.*

class FragmentViewPagerAdapter(fm: FragmentManager, private val num_fragment: Int): FragmentStatePagerAdapter(fm){
    override fun getItem(p0: Int): Fragment? {
        return when(p0){
            0 -> Idx0Fragment()
            1 -> Idx0Fragment()
            2 -> Idx0Fragment()
            3 -> Idx0Fragment()
            4 -> Idx0Fragment()
            5 -> Idx0Fragment()
            6 -> Idx0Fragment()
            7 -> Idx0Fragment()
            else -> null
        }
    }

    override fun getCount(): Int{
        return num_fragment
    }
}