package com.song2.wave

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Size
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.song2.wave.Data.model.SongData

class ArtistSongListViewAdapter(val ctx : Context, val dataList : ArrayList<SongData>) : RecyclerView.Adapter<ArtistSongListViewAdapter.Holder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view : View = LayoutInflater.from(ctx).inflate(R.layout.item_song_view, parent, false)
        return Holder(view)
    }

    override fun getItemCount() : Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        Glide.with(ctx).load(dataList[position].songCoverImg).into(holder.songCoverImg)
        holder.songName.text = dataList[position].songName
        holder.originArtistName.text = dataList[position].originArtistName
        holder.coverArtistName.text = dataList[position].coverArtistName
        holder.songField.text = dataList[position].songField[0] + " " + dataList[position].songField[1] + " " + dataList[position].songField[2]

    }

    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val songCoverImg: ImageView = itemView.findViewById(R.id.img_song_item_cover) as ImageView  //이미지
        val songName : TextView = itemView.findViewById(R.id.tv_song_item_song_name) as TextView //곡 이름
        val originArtistName : TextView = itemView.findViewById(R.id.tv_song_item_origin_artist_name) as TextView   //원곡 아티스트
        var coverArtistName : TextView = itemView.findViewById(R.id.tv_song_item_corver_artist_name) as TextView //커버 아티스트
        val songField : TextView = itemView.findViewById(R.id.tv_song_item_corver_field_name) as TextView         //곡 종류



        //val item_btn : RelativeLayout = itemView.findViewById(R.id.bt_rv_point_history_item) as RelativeLayout
    }
}