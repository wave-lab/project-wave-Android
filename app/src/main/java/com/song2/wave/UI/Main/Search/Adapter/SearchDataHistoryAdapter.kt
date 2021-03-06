package com.song2.wave.UI.Main.Search.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.song2.wave.R
import com.song2.wave.UI.Main.Search.SearchHomeFragment
import com.song2.wave.Util.DB.DBSearchHelper

class SearchDataHistoryAdapter (ctx: Context, val homeFragment: SearchHomeFragment ,private var searchData : ArrayList<String>) : RecyclerView.Adapter<SearchDataHistoryViewHolder>(){

    var searchDbHelper = DBSearchHelper(ctx)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchDataHistoryViewHolder {
        val mainView : View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_search_data, parent, false)
        return SearchDataHistoryViewHolder(mainView)
    }

    override fun getItemCount(): Int = searchData.size

    override fun onBindViewHolder(holder: SearchDataHistoryViewHolder, position: Int) {

        holder.searchDataName.text = searchData[position]

        holder.searchDataName.setOnClickListener {
            homeFragment.performSearch(searchData[position])
            homeFragment.insertKeyword(searchData[position], searchDbHelper)
        }

        holder.searchDataDelete.setOnClickListener {

            var keyword = searchData[position]
            homeFragment.setKeyword(keyword)

            searchData.removeAt(position)
            homeFragment.deleteKeyword(keyword,searchDbHelper)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, searchData.size)
        }
    }
}