package com.song2.wave.UI.Main.Search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.song2.wave.R
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.fragment_search.view.*

class SearchFragment : android.support.v4.app.Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_search,container,false)

        searchFragment = this

        addFragment(SearchHomeFragment())

        v.btn_search_home_frag_searchbar.setOnClickListener {
            var bundle = Bundle()
            bundle.putString("searchData",edit_search_home_frag_searchbar.text.toString())

            val searchhomeFragment = SearchHomeFragment()
            searchhomeFragment.setArguments(bundle)

            replaceFragment(searchhomeFragment)
        }


        return v
    }


    fun addFragment(fragment : android.support.v4.app.Fragment){
        val fm = childFragmentManager
        val transaction = fm.beginTransaction()
        transaction.add(R.id.ll_search_frag_layout, fragment)
        transaction.commit()
    }

    fun replaceFragment(fragment: android.support.v4.app.Fragment)
    {
        val fm = childFragmentManager
        val transaction = fm.beginTransaction()
        transaction.replace(R.id.ll_search_frag_layout, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    companion object {
        lateinit var searchFragment: SearchFragment
    }
}