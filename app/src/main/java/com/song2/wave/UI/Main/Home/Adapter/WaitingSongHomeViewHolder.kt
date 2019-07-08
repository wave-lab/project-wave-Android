package com.song2.wave.UI.Main.Home.Adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.song2.wave.R

class WaitingSongHomeViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    //for test
    var waitingSongContainer : RelativeLayout = itemView!!.findViewById(R.id.rl_item_my_waiting_song_container)
    var songCoverImg: ImageView = itemView!!.findViewById(R.id.iv_item_waiting_song_cover_img)
    var songInfo : TextView = itemView!!.findViewById(R.id.tv_item_waiting_song_item_song_info)
    var artistname : TextView = itemView!!.findViewById(R.id.tv_item_waiting_song_origin_artist_name)
}