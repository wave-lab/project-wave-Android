package com.song2.wave.UI.Main.Home.Top10.Adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.song2.wave.R

class Top10ListViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView){
    var top10Rank : TextView = itemView!!.findViewById(R.id.tv_chart_song_item_rank)
    var top10Img : ImageView = itemView!!.findViewById(R.id.iv_chart_song_item_cover)
    var top10OriginSongInfo : TextView = itemView!!.findViewById(R.id.tv_chart_song_item_origin_song_info)
    var top10CoverArtist : TextView = itemView!!.findViewById(R.id.tv_chart_song_item_cover_artist_name)
    var top10SongField : TextView = itemView!!.findViewById(R.id.tv_chart_song_item_corver_field_name)
}