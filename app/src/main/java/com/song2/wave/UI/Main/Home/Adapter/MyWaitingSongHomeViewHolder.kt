package com.song2.wave.UI.Main.Home.Adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.song2.wave.R

class MyWaitingSongHomeViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    var waitingSongContainer : RelativeLayout = itemView!!.findViewById(R.id.rl_item_my_waiting_song_container)
    var songCoverImg: ImageView = itemView!!.findViewById(R.id.iv_my_waiting_song_cover_img)
    var songDDay : TextView = itemView!!.findViewById(R.id.tv_item_my_waiting_song_d_day)
    var songInfo : TextView = itemView!!.findViewById(R.id.tv_item_my_waiting_song_info)
}