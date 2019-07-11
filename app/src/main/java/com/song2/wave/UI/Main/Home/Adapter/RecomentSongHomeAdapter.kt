package com.song2.wave.UI.Main.Home.Adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.song2.wave.AudioTest.AudioApplication
import com.song2.wave.Data.model.Home.HomeSongData
import com.song2.wave.R
import com.song2.wave.UI.MainPlayer.MainPlayerActivity

class RecomentSongHomeAdapter(var context : Context, private var homeSongData: ArrayList<HomeSongData>, var requestManager : RequestManager) : RecyclerView.Adapter<HomeSongViewHolder>(){

    var mContext = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeSongViewHolder {
        val mainView : View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_home_song, parent, false)
        return HomeSongViewHolder(mainView)
    }

    override fun getItemCount(): Int = homeSongData.size

    override fun onBindViewHolder(holder: HomeSongViewHolder, position: Int) {
        requestManager.load(homeSongData[position].songCoverImg).into(holder.songCoverImg)
        holder.songInfo.text = homeSongData[position].songName +" - "+ homeSongData[position].originArtistName
        holder.coverArtistname.text = homeSongData[position].coverArtistName

        holder.itemView.setOnClickListener{
            var intent = Intent(mContext, MainPlayerActivity::class.java)
            //AudioApplication.getInstance().serviceInterface.setPlayList(holder.adapterPosition) // 재생목록등록
            AudioApplication.getInstance().serviceInterface.play(homeSongData[holder.adapterPosition]._id, homeSongData[holder.adapterPosition].songUrl, homeSongData[position].originArtistName, homeSongData[position].coverArtistName, homeSongData[position].songName) // 선택한 오디오재생
            intent.putExtra("_id", homeSongData[holder.adapterPosition]._id)
            intent.putExtra("songUrl", homeSongData[holder.adapterPosition].songUrl)
            intent.putExtra("title", homeSongData[holder.adapterPosition].songName)
            intent.putExtra("originArtist", homeSongData[holder.adapterPosition].originArtistName)
            intent.putExtra("coverArtist", homeSongData[holder.adapterPosition].coverArtistName)
            mContext.startActivity(intent)
        }

    }
}