package com.song2.wave.UI.Signup

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.song2.wave.R

class SignupArtistViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    var realArtistImg: ImageView = itemView!!.findViewById(R.id.img_artist_select_background)
    var realArtistName : TextView = itemView!!.findViewById(R.id.tv_artist_select_name)
}