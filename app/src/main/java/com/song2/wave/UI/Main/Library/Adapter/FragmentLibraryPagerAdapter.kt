package com.song2.wave.UI.Main.Library.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.song2.wave.UI.Main.Library.LibraryLikeFragment
import com.song2.wave.UI.Main.Library.LibraryMyPlaylistFragment
import com.song2.wave.UI.Main.Library.LibraryPlayListFragment

class FragmentLibraryPagerAdapter(fm: FragmentManager, private val num_fragment: Int): FragmentStatePagerAdapter(fm){
    override fun getItem(p0: Int): Fragment? {
        return when(p0){
            0 -> LibraryPlayListFragment()
            1 -> LibraryLikeFragment()
            2 -> LibraryMyPlaylistFragment()
            else -> null
        }
    }

    override fun getCount(): Int{
        return num_fragment
    }
}