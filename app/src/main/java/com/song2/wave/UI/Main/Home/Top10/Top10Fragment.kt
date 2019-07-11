package com.song2.wave.UI.Main.Home.Top10

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.song2.wave.R
import com.song2.wave.UI.Main.Home.Top10.Adapter.FragmentViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_top10.*
import kotlinx.android.synthetic.main.fragment_top10_tabbar.*
import kotlinx.android.synthetic.main.fragment_top10_tabbar.view.*


class Top10Fragment: Fragment(){
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configureTopNavigation()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v = inflater.inflate(R.layout.fragment_top10, container, false)
        return v
    }

    private fun configureTopNavigation() {

        var textViewList : ArrayList<TextView?> = arrayListOf(tv_top10_tabbar_idx0, tv_top10_tabbar_idx1,tv_top10_tabbar_idx2, tv_top10_tabbar_idx3, tv_top10_tabbar_idx4, tv_top10_tabbar_idx5, tv_top10_tabbar_idx6, tv_top10_tabbar_idx7)

        if (getArguments()!!.getString("genreId") != null){



        }

        //var data = getArguments()!!.getStringArrayList("genreRank")
/*
        for(i in data.indices){
            Log.e("data[i]",data[i])
            textViewList[i]?.setText(data[i])

            //textViewList[i]!!.text = data[i]
            //tv_top10_tabbar_idx0.setText(data[i])
        }*/

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