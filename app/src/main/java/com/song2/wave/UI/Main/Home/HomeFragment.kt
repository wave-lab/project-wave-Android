package com.song2.wave.UI.Main.Home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.song2.wave.R
import com.song2.wave.UI.MainPlayer.MainPlayerActivity
import com.song2.wave.UI.MusicTestActivity
import com.song2.wave.UI.Main.Home.Adapter.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : android.support.v4.app.Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_home,container,false)

        homeFragment = this

        addFragment(HomeOnFragment())

/*        v.tv_home_frag_service_test.setOnClickListener {
            var intent = Intent(context, MusicTestActivity::class.java)
            startActivity(intent)
        }*/


        return v
    }


    fun addFragment(fragment : android.support.v4.app.Fragment){
        val fm = childFragmentManager
        val transaction = fm.beginTransaction()
        transaction.add(R.id.ll_home_frag_layout, fragment)
        transaction.commit()
    }

    fun replaceFragment(fragment: android.support.v4.app.Fragment)
    {
        val fm = childFragmentManager
        val transaction = fm.beginTransaction()
        transaction.replace(R.id.ll_home_frag_layout, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    companion object {
        lateinit var homeFragment: HomeFragment
    }
}