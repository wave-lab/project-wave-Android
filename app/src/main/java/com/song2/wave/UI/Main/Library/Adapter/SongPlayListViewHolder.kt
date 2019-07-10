package com.song2.wave.UI.Main.Library.Adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.song2.wave.R

class SongPlayListViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView){

    var playListImg0 :ImageView = itemView!!.findViewById(R.id.img_one)
    var playListImg1 :ImageView = itemView!!.findViewById(R.id.img_two)
    var playListImg2 :ImageView = itemView!!.findViewById(R.id.img_three)
    var playListImg3 :ImageView = itemView!!.findViewById(R.id.img_four)

    var playListName : TextView  = itemView!!.findViewById(R.id.tv_artist_playlist_name)
    var playListComment : TextView = itemView!!.findViewById(R.id.tv_artist_comment)

    var playListMoreBtn : ImageView = itemView!!.findViewById(R.id.iv_playlist_item_more_btn)


}