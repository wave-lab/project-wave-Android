package com.song2.wave.UI.Main.MyPage.Adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.song2.wave.R

class ScoreResultViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView){
    var songCoverImg : ImageView = itemView!!.findViewById(R.id.iv_scoring_result_song_item_cover)
    var songOriginInfo : TextView = itemView!!.findViewById(R.id.tv_scoring_result_song_item_origin_song_info)
    var songResultScore : TextView = itemView!!.findViewById(R.id.tv_scoring_result_song_item_score)
    var songField : TextView = itemView!!.findViewById(R.id.tv_scoring_result_song_item_corver_field)
}