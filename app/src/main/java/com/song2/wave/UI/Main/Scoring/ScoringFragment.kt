package com.song2.wave.UI.Main.Scoring


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.song2.wave.R
import com.song2.wave.UI.Main.Scoring.Adapter.FragmentScoringPagerAdapter
import kotlinx.android.synthetic.main.fragment_scoring.*


class ScoringFragment : Fragment() {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configureTopNavigation()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_scoring, container, false)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_main)
        //rv_scoring_song_passed.adapter = ScoringPassAdapter()
        //rv_scoring_song_passed.layoutManager = LinearLayoutManager(this)
    }


    private fun configureTopNavigation() {
        vp_scoring.adapter = FragmentScoringPagerAdapter(childFragmentManager,2)
        // ViewPager와 Tablayout을 엮어줍니다!
        tl_scoring_tabbar.setupWithViewPager(vp_scoring)
        //TabLayout에 붙일 layout을 찾아준 다음
        val topNaviLayout: View = this.layoutInflater.inflate(R.layout.fragment_scoring_tabbar, null, false)
        //탭 하나하나 TabLayout에 연결시켜줍니다.
        tl_scoring_tabbar.getTabAt(0)!!.customView = topNaviLayout.findViewById(R.id.rl_scoring_tabbar_frag_waiting) as RelativeLayout
        tl_scoring_tabbar.getTabAt(1)!!.customView = topNaviLayout.findViewById(R.id.rl_scoring_tabbar_frag_completed) as RelativeLayout

    }
}
