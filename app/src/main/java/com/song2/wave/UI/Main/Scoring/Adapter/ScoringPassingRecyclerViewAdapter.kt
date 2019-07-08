package com.song2.wave.UI.Main.Scoring.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.song2.wave.Data.model.Scoring.PassedSongData
import com.song2.wave.R

class ScoringPassingRecyclerViewAdapter (val ctx: Context, val datalist: ArrayList<PassedSongData>): RecyclerView.Adapter<ScoringPassingRecyclerViewAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.item_song_passing, parent,false)
        return Holder(view)
    }

    override fun getItemCount(): Int =datalist.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        Glide.with(ctx)
            .load(datalist[position].songCoverImg)
            .into(holder.songCoverImg)
        //holder.songName.text= datalist[position].songName
        holder.songInfo.text = datalist[position].songName +" - "+ datalist[position].originArtistName
        holder.coverArtistName.text= datalist[position].coverArtistName
    }

    inner class Holder(ItemView : View): RecyclerView.ViewHolder(ItemView){
        var songCoverImg = itemView.findViewById(R.id.img_cover_artist_scoring_passing) as ImageView
        //var songName = itemView.findViewById(R.id.txt_cover_songName_scoring_passing) as TextView
        var songInfo = itemView.findViewById(R.id.txt_cover_songInfo_scoring_passing) as TextView
        var coverArtistName = itemView.findViewById(R.id.txt_cover_artistName_scoring_passing) as TextView


    }
}
