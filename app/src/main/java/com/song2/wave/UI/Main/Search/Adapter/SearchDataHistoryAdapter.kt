package com.song2.wave.UI.Main.Search.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.song2.wave.R

class SearchDataHistoryAdapter (private var searchData : ArrayList<String>) : RecyclerView.Adapter<SearchDataHistoryViewHolder>(){

    var EXAMPLE_IMG_URL : String = "https://t1.daumcdn.net/cfile/tistory/2442394558BBBD1934"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchDataHistoryViewHolder {
        val mainView : View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_search_data, parent, false)
        return SearchDataHistoryViewHolder(mainView)
    }

    override fun getItemCount(): Int = searchData.size

    override fun onBindViewHolder(holder: SearchDataHistoryViewHolder, position: Int) {

        //requestManager.load(songData[position].songCoverImg).centerCrop().into(holder.songCoverImg)
        // ex)
        holder.searchDataName.text = searchData[position]
    }
}