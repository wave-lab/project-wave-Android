package com.song2.wave.UI.Main.Home.Adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.song2.wave.R

class HomeSongViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    var songContainer : RelativeLayout = itemView!!.findViewById(R.id.rl_item_home_song_container)
    var songCoverImg: ImageView = itemView!!.findViewById(R.id.iv_item_home_song_cover_img)
    var songInfo : TextView = itemView!!.findViewById(R.id.tv_item_home_song_song_info)
    var coverArtistname : TextView = itemView!!.findViewById(R.id.tv_item_home_song_artist)
}
