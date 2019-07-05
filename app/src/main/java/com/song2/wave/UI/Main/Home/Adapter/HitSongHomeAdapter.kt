package com.song2.wave.UI.Main.Home.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.song2.wave.Data.model.Home.HomeSongData
import com.song2.wave.R

class HitSongHomeAdapter(private var homeSongData: ArrayList<HomeSongData>, var requestManager : RequestManager) : RecyclerView.Adapter<HomeSongViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeSongViewHolder {
        val mainView : View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_home_song, parent, false)
        return HomeSongViewHolder(mainView)
    }

    override fun getItemCount(): Int = homeSongData.size

    override fun onBindViewHolder(holder: HomeSongViewHolder, position: Int) {
        requestManager.load(homeSongData[position].songCoverImg).into(holder.songCoverImg)
        holder.songInfo.text = homeSongData[position].songName +" - "+ homeSongData[position].originArtistName
        holder.coverArtistname.text =
                homeSongData[position].coverArtistName
    }
}