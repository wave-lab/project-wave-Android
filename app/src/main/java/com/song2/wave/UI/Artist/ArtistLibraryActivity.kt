package com.song2.wave.UI.Artist

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.song2.wave.R
import com.song2.wave.UI.Artist.Adapter.FragmentArtistAdapter
import kotlinx.android.synthetic.main.activity_artist_library.*

class ArtistLibraryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist_library)

        configureMainTab()
    }

    override fun onResume() {
        super.onResume()
    }


    private fun configureMainTab() {

        vp_artist_library.adapter = FragmentArtistAdapter(supportFragmentManager, 2)
        vp_artist_library.offscreenPageLimit = 2
        tl_artist_library.setupWithViewPager(vp_artist_library)

        val navCategoryMainLayout: View = (this.getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
                .inflate(R.layout.fragment_artist_library_tabbar, null, false)
        tl_artist_library.getTabAt(0)!!.customView =
            navCategoryMainLayout.findViewById(R.id.rl_artist_library_tabbar_like) as RelativeLayout
        tl_artist_library.getTabAt(1)!!.customView =
            navCategoryMainLayout.findViewById(R.id.rl_artist_library_tabbar_playlist) as RelativeLayout

    }

}
