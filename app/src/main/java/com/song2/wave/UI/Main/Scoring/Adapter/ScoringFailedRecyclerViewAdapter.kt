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
import com.song2.wave.UI.Main.Scoring.Adapter.ScoringFailedRecyclerViewAdapter.Holder as Holder

class  ScoringFailedRecyclerViewAdapter(val ctx: Context, val datalist: ArrayList<PassedSongData>): RecyclerView.Adapter<Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder{

        val view: View = LayoutInflater.from(ctx).inflate(R.layout.item_song_failed, parent,false)
        return Holder(view)
    }

    override fun getItemCount(): Int =datalist.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        Glide.with(ctx)
            .load(datalist[position].songCoverImg)
            .into(holder.songCoverImg)
        holder.songInfo.text = datalist[position].songName +" - "+ datalist[position].originArtistName
        holder.coverArtistName.text= datalist[position].coverArtistName

    }

    inner class Holder(ItemView : View): RecyclerView.ViewHolder(ItemView){
        var songCoverImg = itemView.findViewById(R.id.img_cover_artist_scoring_failed) as ImageView
        //var songName = itemView.findViewById(R.id.txt_cover_songName_scoring_failed) as TextView
        var songInfo = itemView.findViewById(R.id.txt_cover_songInfo_scoring_failed) as TextView
        var coverArtistName = itemView.findViewById(R.id.txt_cover_artistName_scoring_failed) as TextView


    }
}
