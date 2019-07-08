package com.song2.wave.UI.Main.MyPage.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.song2.wave.Data.model.MyPage.HitSuccessSongData
import com.song2.wave.R

class MyPageHitSuccessAdapter(val ctx: Context, val datalist: ArrayList<HitSuccessSongData>): RecyclerView.Adapter<MyPageHitSuccessAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.item_mypage_hit_success, parent,false)
        return Holder(view)
    }

    override fun getItemCount(): Int =datalist.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        Glide.with(ctx)
            .load(datalist[position].songCoverImg)
            .into(holder.songCoverImg)
        holder.songName.text = datalist[position].songName
        holder.songOriginArtist.text = "- "+datalist[position].originArtistName
        holder.coverArtistName.text = datalist[position].coverArtistName
        holder.songField.text = datalist[position].songField[0]
        holder.avgScore.text = datalist[position].avgScore.toString()
        holder.myScore.text = datalist[position].myScore.toString()


    }

    inner class Holder(ItemView : View): RecyclerView.ViewHolder(ItemView){

        var songCoverImg = itemView.findViewById(R.id.img_hit_success) as ImageView
        var songName = itemView.findViewById(R.id.tv_hit_success_song_item_song_name) as TextView
        var songOriginArtist = itemView.findViewById(R.id.tv_hit_success_song_item_origin_artist_name) as TextView
        var coverArtistName = itemView.findViewById(R.id.tv_hit_success_song_item_corver_artist_name) as TextView
        var songField = itemView.findViewById(R.id.tv_hit_success_song_item_corver_field_name) as TextView
        var avgScore = itemView.findViewById(R.id.tv_avg_score) as TextView
        var myScore = itemView.findViewById(R.id.tv_my_score) as TextView
    }
}

