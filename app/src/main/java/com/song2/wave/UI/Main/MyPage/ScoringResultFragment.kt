package com.song2.wave.UI.Main.MyPage

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.song2.wave.R
import com.song2.wave.UI.Main.MyPage.Adapter.FragmentMypageScoringResultPagerAdapter
import kotlinx.android.synthetic.main.fragment_scoring_result.*

class ScoringResultFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return return inflater.inflate(R.layout.fragment_scoring_result, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configureTopNavigation()
    }

    private fun configureTopNavigation() {
        vp_scoring_result_frag_content.adapter = FragmentMypageScoringResultPagerAdapter(childFragmentManager, 3)
        // ViewPager와 Tablayout을 엮어줍니다!
        tl_scoring_result_frag_tabbar.setupWithViewPager(vp_scoring_result_frag_content)
        //TabLayout에 붙일 layout을 찾아준 다음
        val topNaviLayout: View = this.layoutInflater.inflate(R.layout.fragment_mypage_score_result_tabbar, null, false)
        //탭 하나하나 TabLayout에 연결시켜줍니다.
        tl_scoring_result_frag_tabbar.getTabAt(0)!!.customView =
                topNaviLayout.findViewById(R.id.rl_mypage_tabbar_score_passed) as RelativeLayout
        tl_scoring_result_frag_tabbar.getTabAt(1)!!.customView =
                topNaviLayout.findViewById(R.id.rl_mypage_tabbar_score_waiting) as RelativeLayout
        tl_scoring_result_frag_tabbar.getTabAt(2)!!.customView =
                topNaviLayout.findViewById(R.id.rl_mypage_tabbar_score_fail) as RelativeLayout
    }
}