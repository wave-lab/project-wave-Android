package com.song2.wave.UI.Main.Search.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.song2.wave.R
import com.song2.wave.R.id.edit_search_home_frag_searchbar
import com.song2.wave.UI.Main.Search.SearchHomeFragment
import com.song2.wave.UI.Main.Search.SearchHomeFragment.Companion.searchHomeFragment
import com.song2.wave.Util.DB.DBSearchHelper
import kotlinx.android.synthetic.main.fragment_search_home.*

class SearchDataHistoryAdapter (ctx: Context ,private var searchData : ArrayList<String>) : RecyclerView.Adapter<SearchDataHistoryViewHolder>(){

    var searchDbHelper = DBSearchHelper(ctx)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchDataHistoryViewHolder {
        val mainView : View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_search_data, parent, false)
        return SearchDataHistoryViewHolder(mainView)
    }

    override fun getItemCount(): Int = searchData.size

    override fun onBindViewHolder(holder: SearchDataHistoryViewHolder, position: Int) {

        searchHomeFragment = SearchHomeFragment()

        holder.searchDataName.text = searchData[position]

/*        holder.searchDataName.setOnClickListener {
            searchHomeFragment.setSearchBarText(searchData[position])
            searchHomeFragment.performSearch()
            searchHomeFragment.insertKeyword(searchData[position], searchDbHelper)
        }*/

        holder.searchDataDelete.setOnClickListener {
            searchHomeFragment.deleteKeyword(searchData[position],searchDbHelper)
            notifyItemRemoved(position)
        }
    }
}