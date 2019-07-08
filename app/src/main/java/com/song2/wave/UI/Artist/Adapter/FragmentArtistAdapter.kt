package com.song2.wave.UI.Artist.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.song2.wave.UI.Artist.ArtistLikeSongFragment
import com.song2.wave.UI.Artist.ArtistPlaylistFragment

class FragmentArtistAdapter(fm: FragmentManager, private val num_fragment: Int) :
    FragmentStatePagerAdapter(fm) {
    override fun getItem(p0: Int): Fragment? {
        return when (p0) {
            0 -> ArtistLikeSongFragment()
            1 -> ArtistPlaylistFragment()
            else -> null
        }
    }

    override fun getCount(): Int {
        return num_fragment
    }
}
