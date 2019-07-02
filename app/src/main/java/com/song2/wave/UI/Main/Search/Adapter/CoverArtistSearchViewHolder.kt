package com.song2.wave.UI.Main.Search.Adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.song2.wave.R

class CoverArtistSearchViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    var coverArtistImg: ImageView = itemView!!.findViewById(R.id.img_cover_artist_item_profile)
    var coverArtistName : TextView = itemView!!.findViewById(R.id.tv_cover_artist_item_name)
}