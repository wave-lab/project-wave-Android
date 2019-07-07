package com.song2.wave.UI.Main.Home.Top10

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.song2.wave.R
import com.song2.wave.UI.Main.Home.Top10.Adapter.FragmentViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_top10.*


class Top10Fragment: Fragment(){
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configureTopNavigation()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_top10, container, false)
    }

    private fun configureTopNavigation() {
        vp_top10_frag_content.adapter = FragmentViewPagerAdapter(childFragmentManager,8)
        // ViewPager와 Tablayout을 엮어줍니다!
        tl_top10_frag_tabbar.setupWithViewPager(vp_top10_frag_content)
        //TabLayout에 붙일 layout을 찾아준 다음
        val topNaviLayout: View = this.layoutInflater.inflate(R.layout.fragment_top10_tabbar, null, false)
        //탭 하나하나 TabLayout에 연결시켜줍니다.
        tl_top10_frag_tabbar.getTabAt(0)!!.customView = topNaviLayout.findViewById(R.id.rl_top10_frag_idx0) as RelativeLayout
        tl_top10_frag_tabbar.getTabAt(1)!!.customView = topNaviLayout.findViewById(R.id.rl_top10_frag_idx1) as RelativeLayout
        tl_top10_frag_tabbar.getTabAt(2)!!.customView = topNaviLayout.findViewById(R.id.rl_top10_frag_idx2) as RelativeLayout
        tl_top10_frag_tabbar.getTabAt(3)!!.customView = topNaviLayout.findViewById(R.id.rl_top10_frag_idx3) as RelativeLayout
        tl_top10_frag_tabbar.getTabAt(4)!!.customView = topNaviLayout.findViewById(R.id.rl_top10_frag_idx4) as RelativeLayout
        tl_top10_frag_tabbar.getTabAt(5)!!.customView = topNaviLayout.findViewById(R.id.rl_top10_frag_idx5) as RelativeLayout
        tl_top10_frag_tabbar.getTabAt(6)!!.customView = topNaviLayout.findViewById(R.id.rl_top10_frag_idx6) as RelativeLayout
        tl_top10_frag_tabbar.getTabAt(7)!!.customView = topNaviLayout.findViewById(R.id.rl_top10_frag_idx7) as RelativeLayout
    }
}