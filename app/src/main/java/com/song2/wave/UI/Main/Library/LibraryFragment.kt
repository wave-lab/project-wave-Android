package com.song2.wave.UI.Main.Library

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.song2.wave.R
import com.song2.wave.UI.Main.Library.Adapter.FragmentLibraryPagerAdapter
import kotlinx.android.synthetic.main.fragment_library.*

class LibraryFragment: Fragment(){
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configureTopNavigation()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_library, container, false)
    }

    private fun configureTopNavigation() {
        vp_library_frag_content.adapter = FragmentLibraryPagerAdapter(childFragmentManager,3)
        // ViewPager와 Tablayout을 엮어줍니다!
        tl_library_frag_tabbar.setupWithViewPager(vp_library_frag_content)
        //TabLayout에 붙일 layout을 찾아준 다음
        val topNaviLayout: View = this.layoutInflater.inflate(R.layout.fragment_library_tabbar, null, false)
        //탭 하나하나 TabLayout에 연결시켜줍니다.
        tl_library_frag_tabbar.getTabAt(0)!!.customView = topNaviLayout.findViewById(R.id.rl_library_tabbar_frag_playlist) as RelativeLayout
        tl_library_frag_tabbar.getTabAt(1)!!.customView = topNaviLayout.findViewById(R.id.rl_library_tabbar_frag_like) as RelativeLayout
        tl_library_frag_tabbar.getTabAt(2)!!.customView = topNaviLayout.findViewById(R.id.rl_library_tabbar_frag_my_playlist) as RelativeLayout
    }
}