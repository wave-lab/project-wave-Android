package com.song2.wave.UI.Main.Search.Adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.song2.wave.R

class SearchDataHistoryViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    var searchDataName: TextView = itemView!!.findViewById(R.id.tv_search_item_search_data)
    var searchDataDelete : ImageView = itemView!!.findViewById(R.id.iv_search_home_frag_delete_keyword_item)
}