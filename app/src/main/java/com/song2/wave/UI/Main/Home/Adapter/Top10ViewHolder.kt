package com.song2.wave.UI.Main.Home.Adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.song2.wave.R

class Top10ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView){
    var top10ItemContainer : RelativeLayout = itemView!!.findViewById(R.id.rl_top10_item_container)
    var top10CoverImg: ImageView = itemView!!.findViewById(R.id.iv_top10_item_kinds_img)
    var top10Kinds : TextView = itemView!!.findViewById(R.id.tv_top10_item_song_kinds)
}