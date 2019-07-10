package com.song2.wave.UI.Main.MyPage

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.song2.wave.Data.model.Mypage.ScoreHitResultData
import com.song2.wave.R
import com.song2.wave.UI.Main.MyPage.Adapter.FragmentMypageScoringResultPagerAdapter
import com.song2.wave.UI.Main.MyPage.Adapter.HitScoreResultAdapter
import kotlinx.android.synthetic.main.fragment_my_page.*

class MyPageFragment : Fragment() {

    lateinit var hitScoreResultAdapter: HitScoreResultAdapter
    lateinit var scoreHitResultData: ArrayList<ScoreHitResultData>
    lateinit var requestManager: RequestManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return return inflater.inflate(R.layout.fragment_my_page, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configureTopNavigation()

        attachRecyclerView()
    }

    fun attachRecyclerView() {
        scoreHitResultData = ArrayList()
        requestManager = Glide.with(this)

        insertExampleData()

        hitScoreResultAdapter = HitScoreResultAdapter(scoreHitResultData, requestManager)
        rv_mypage_frag_score_hit_result.adapter = hitScoreResultAdapter
        rv_mypage_frag_score_hit_result.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)


    }


    fun insertExampleData() {

        scoreHitResultData.add(ScoreHitResultData(false,"https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "똥꼬","지훈", "승희"))
        scoreHitResultData.add(ScoreHitResultData(true,"https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "똥꼬","지훈", "승희"))
        scoreHitResultData.add(ScoreHitResultData(false,"https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "똥꼬","지훈", "승희"))
        scoreHitResultData.add(ScoreHitResultData(true,"https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "똥꼬","지훈", "승희"))
        scoreHitResultData.add(ScoreHitResultData(true,"https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "똥꼬","지훈", "승희"))
        scoreHitResultData.add(ScoreHitResultData(false,"https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "똥꼬","지훈", "승희"))
        scoreHitResultData.add(ScoreHitResultData(false,"https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "똥꼬","지훈", "승희"))
        scoreHitResultData.add(ScoreHitResultData(true,"https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "똥꼬","지훈", "승희"))

    }

    private fun configureTopNavigation() {
        vp_mypage_frag_content.adapter = FragmentMypageScoringResultPagerAdapter(childFragmentManager, 3)
        // ViewPager와 Tablayout을 엮어줍니다!
        tl_mypage_frag_tabbar.setupWithViewPager(vp_mypage_frag_content)
        //TabLayout에 붙일 layout을 찾아준 다음
        val topNaviLayout: View = this.layoutInflater.inflate(R.layout.fragment_mypage_score_result_tabbar, null, false)
        //탭 하나하나 TabLayout에 연결시켜줍니다.
        tl_mypage_frag_tabbar.getTabAt(0)!!.customView =
                topNaviLayout.findViewById(R.id.rl_mypage_tabbar_score_passed) as RelativeLayout
        tl_mypage_frag_tabbar.getTabAt(1)!!.customView =
                topNaviLayout.findViewById(R.id.rl_mypage_tabbar_score_waiting) as RelativeLayout
        tl_mypage_frag_tabbar.getTabAt(2)!!.customView =
                topNaviLayout.findViewById(R.id.rl_mypage_tabbar_score_fail) as RelativeLayout
    }
}