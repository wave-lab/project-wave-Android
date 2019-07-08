package com.song2.wave.UI.Main.MyPage.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.song2.wave.UI.Main.MyPage.MypageScoringFailFragment
import com.song2.wave.UI.Main.MyPage.MypageScoringPassedFragment
import com.song2.wave.UI.Main.MyPage.MypageScoringWaitingFragment


class FragmentMypageScoringResultPagerAdapter(fm: FragmentManager, private val num_fragment: Int): FragmentStatePagerAdapter(fm){
    override fun getItem(p0: Int): Fragment? {
        return when(p0){
            0 -> MypageScoringPassedFragment()
            1 -> MypageScoringWaitingFragment()
            2 -> MypageScoringFailFragment()
            else -> null
        }
    }

    override fun getCount(): Int{
        return num_fragment
    }
}