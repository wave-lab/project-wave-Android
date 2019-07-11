package com.song2.wave.UI.Main.Search.Adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

import com.song2.wave.R

class SongSearchViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {


    var songCoverImg: ImageView = itemView!!.findViewById(R.id.img_song_item_cover)
    var songName : TextView = itemView!!.findViewById(R.id.tv_song_item_song_name)
    var originArtistName: TextView = itemView!!.findViewById(R.id.tv_song_item_origin_artist_name)
    var coverArtistName: TextView = itemView!!.findViewById(R.id.tv_song_item_corver_artist_name)
    var songField: TextView = itemView!!.findViewById(R.id.tv_song_item_corver_field_name)
}