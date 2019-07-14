package com.song2.wave.UI.Main.Scoring.Adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.song2.wave.Data.model.Home.HomeSongData
import com.song2.wave.Data.model.Scoring.PassedCompletedSongData
import com.song2.wave.Data.model.Scoring.PassedSongData
import com.song2.wave.Data.model.SongData
import com.song2.wave.R
import com.song2.wave.UI.Main.Home.Adapter.WaitingSongHomeViewHolder
import com.song2.wave.UI.MainPlayer.MainPlayerActivity
import com.song2.wave.Util.Audio.AudioApplication

class ScoringPassedRecyclerViewAdapter(val ctx: Context, val datalist: ArrayList<SongData>): RecyclerView.Adapter<ScoringPassedRecyclerViewAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.item_song_passed, parent,false)
        return Holder(view)
    }

    override fun getItemCount(): Int =datalist.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        Glide.with(ctx)
            .load(datalist[position].songCoverImg)
            .into(holder.songCoverImg)
        holder.songName.text= datalist[position].songName
        holder.coverArtistName.text= datalist[position].coverArtistName

        holder.itemView.setOnClickListener{
            var intent = Intent(ctx, MainPlayerActivity::class.java)
            //AudioApplication.getInstance().serviceInterface.setPlayList(holder.adapterPosition) // 재생목록등록
            AudioApplication.getInstance().serviceInterface.play(datalist[holder.adapterPosition]._id, datalist[holder.adapterPosition].songUrl, datalist[position].originArtistName, datalist[position].coverArtistName, datalist[position].songName) // 선택한 오디오재생
            intent.putExtra("_id", datalist[holder.adapterPosition]._id)
            intent.putExtra("songUrl", datalist[holder.adapterPosition].songUrl)
            intent.putExtra("title", datalist[holder.adapterPosition].songName)
            intent.putExtra("originArtist", datalist[holder.adapterPosition].originArtistName)
            intent.putExtra("coverArtist", datalist[holder.adapterPosition].coverArtistName)
            intent.putExtra("songImgUrl", datalist[holder.adapterPosition].songCoverImg)
            intent.putExtra("rating_flag", 1)
            ctx.startActivity(intent)
        }


    }

    inner class Holder(ItemView : View): RecyclerView.ViewHolder(ItemView){
        var songCoverImg = itemView.findViewById(R.id.img_cover_artist_scoring_passed) as ImageView
        var songName = itemView.findViewById(R.id.txt_cover_songName_scoring_passed) as TextView
        var coverArtistName = itemView.findViewById(R.id.txt_cover_artistName_scoring_passed) as TextView


    }
}
