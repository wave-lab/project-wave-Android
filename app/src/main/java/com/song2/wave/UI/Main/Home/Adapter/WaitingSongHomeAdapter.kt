package com.song2.wave.UI.Main.Home.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.song2.wave.Data.model.Home.HomeSongData
import com.song2.wave.R


class WaitingSongHomeAdapter (private var waitingSongData: ArrayList<HomeSongData>, var requestManager : RequestManager) : RecyclerView.Adapter<WaitingSongHomeViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WaitingSongHomeViewHolder {
        val mainView : View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_waiting_song, parent, false)
        return WaitingSongHomeViewHolder(mainView)
    }

    override fun getItemCount(): Int = waitingSongData.size

    override fun onBindViewHolder(holder: WaitingSongHomeViewHolder, position: Int) {
        requestManager.load(waitingSongData[position].songCoverImg).into(holder.songCoverImg)
        holder.songInfo.text = waitingSongData[position].songName +" - "+ waitingSongData[position].originArtistName
        holder.artistname.text = waitingSongData[position].coverArtistName
    }
}
