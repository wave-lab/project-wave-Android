package com.song2.wave.UI.Main.Scoring.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.song2.wave.Data.model.SongData
import com.song2.wave.R
import com.song2.wave.UI.Main.Search.Adapter.SongSearchViewHolder

class ScoringWaitAdapter (private var songData : ArrayList<SongData>, var requestManager : RequestManager) : RecyclerView.Adapter<SongSearchViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongSearchViewHolder {
        val mainView : View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_song_view, parent, false)
        return SongSearchViewHolder(mainView)
    }

    override fun getItemCount(): Int = songData.size

    override fun onBindViewHolder(holder: SongSearchViewHolder, position: Int) {

        requestManager.load(songData[position].songCoverImg).centerCrop().into(holder.songCoverImg)
        holder.songName.text = songData[position].songName
        holder.originArtistName.text = " - " + songData[position].originArtistName
        holder.coverArtistName.text = songData[position].coverArtistName
        holder.songField.text = songData[position].songField!![0]
    }
}