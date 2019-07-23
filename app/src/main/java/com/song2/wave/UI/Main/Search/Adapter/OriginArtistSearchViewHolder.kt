package com.song2.wave.UI.Main.Search.Adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.song2.wave.R

class OriginArtistSearchViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    var originArtistContainer : LinearLayout = itemView!!.findViewById(R.id.ll_cover_artist_item_container)
    var originArtistImg: ImageView = itemView!!.findViewById(R.id.img_cover_artist_item_profile)
    var originArtistName : TextView = itemView!!.findViewById(R.id.tv_cover_artist_item_name)
}