package com.song2.wave.UI.Main.MyPage.Adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.song2.wave.R

class HitScoreResultViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    var failFilter : RelativeLayout = itemView!!.findViewById(R.id.rl_hit_result_item_fail)
    var passFilter : RelativeLayout = itemView!!.findViewById(R.id.rl_hit_result_item_pass)
    var songContainer : RelativeLayout = itemView!!.findViewById(R.id.rl_item_home_hit_song_result_container)
    var songCoverImg: ImageView = itemView!!.findViewById(R.id.iv_song_hit_result_item_coverImg)
    var songInfo : TextView = itemView!!.findViewById(R.id.tv_song_hit_result_item_songInfo)
    var coverArtistname : TextView = itemView!!.findViewById(R.id.tv_song_hit_result_item_cover_artist)
}
