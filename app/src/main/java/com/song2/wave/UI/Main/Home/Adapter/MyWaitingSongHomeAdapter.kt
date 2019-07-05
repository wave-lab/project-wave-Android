package com.song2.wave.UI.Main.Home.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.song2.wave.Data.model.Home.MyWaitingSongData
import com.song2.wave.R

class MyWaitingSongHomeAdapter (private var waitingSongData: ArrayList<MyWaitingSongData>, var requestManager : RequestManager) : RecyclerView.Adapter<MyWaitingSongHomeViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyWaitingSongHomeViewHolder {
        val mainView : View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_my_waiting_song, parent, false)
        return MyWaitingSongHomeViewHolder(mainView)
    }

    override fun getItemCount(): Int = waitingSongData.size

    override fun onBindViewHolder(holder: MyWaitingSongHomeViewHolder, position: Int) {
        requestManager.load(waitingSongData[position].songCoverImg_mine).into(holder.songCoverImg)
        holder.songInfo.text = waitingSongData[position].songName_mine +" - "+ waitingSongData[position].originArtistName_mine
        holder.songDDay.text = "D - "+ waitingSongData[position].songWaitingDay_mine
    }
}