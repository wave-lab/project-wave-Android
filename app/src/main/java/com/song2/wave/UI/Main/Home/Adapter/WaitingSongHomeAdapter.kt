package com.song2.wave.UI.Main.Home.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.song2.wave.Data.model.Home.WaitingSongData
import com.song2.wave.R

class WaitingSongHomeAdapter (private var waitingSongData: ArrayList<WaitingSongData>, var requestManager : RequestManager) : RecyclerView.Adapter<WaitingSongHomeViewHolder>(){
    
    var EXAMPLE_IMG_URL : String = "https://t1.daumcdn.net/cfile/tistory/2641FF4C5900DDDE1E"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WaitingSongHomeViewHolder {
        val mainView : View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_waiting_song, parent, false)
        return WaitingSongHomeViewHolder(mainView)
    }

    override fun getItemCount(): Int = waitingSongData.size

    override fun onBindViewHolder(holder: WaitingSongHomeViewHolder, position: Int) {

        requestManager.load(EXAMPLE_IMG_URL).into(holder.songCoverImg)
        holder.songDDay.text = "D - " + waitingSongData[position].songWaitingDay.toString()
        holder.songInfo.text = waitingSongData[position].songName + waitingSongData[position].originArtistName
    }
}