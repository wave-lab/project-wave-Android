package com.song2.wave.UI.Artist

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import com.song2.wave.R
import com.song2.wave.UI.Artist.Adapter.FragmentArtistAdapter
import kotlinx.android.synthetic.main.activity_artist.*

class ArtistActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist)
        configureTopNavigation()
    }


    private fun configureTopNavigation() {
        vp_artist_act_artist_library.adapter = FragmentArtistAdapter(supportFragmentManager,2)
        // ViewPager와 Tablayout을 엮어줍니다!
        tl_artist_act_artist_library.setupWithViewPager(vp_artist_act_artist_library)
        //TabLayout에 붙일 layout을 찾아준 다음
        val topNaviLayout: View = this.layoutInflater.inflate(R.layout.fragment_artist_library_tabbar, null, false)
        //탭 하나하나 TabLayout에 연결시켜줍니다.
        tl_artist_act_artist_library.getTabAt(0)!!.customView = topNaviLayout.findViewById(R.id.rl_artist_library_tabbar_like) as RelativeLayout
        tl_artist_act_artist_library.getTabAt(1)!!.customView = topNaviLayout.findViewById(R.id.rl_artist_library_tabbar_playlist) as RelativeLayout
    }

}
