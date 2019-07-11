package com.song2.wave.UI.Main.Search.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.song2.wave.Data.model.SongData
import com.song2.wave.R

class SongSearchAdapter (private var songData : ArrayList<SongData>, var requestManager : RequestManager) : RecyclerView.Adapter<SongSearchViewHolder>(){

    var EXAMPLE_IMG_URL : String = "https://t1.daumcdn.net/cfile/tistory/2442394558BBBD1934"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongSearchViewHolder {
        val mainView : View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_song_view, parent, false)
        return SongSearchViewHolder(mainView)
    }

    override fun getItemCount(): Int = songData.size

    override fun onBindViewHolder(holder: SongSearchViewHolder, position: Int) {

        requestManager.load(songData[position].songCoverImg).centerCrop().into(holder.songCoverImg)
        // ex)
        //requestManager.load(EXAMPLE_IMG_URL).into(holder.songCoverImg)
        holder.songName.text = songData[position].songName
        holder.originArtistName.text = " - " + songData[position].originArtistName
        holder.coverArtistName.text = songData[position].coverArtistName
        holder.songField.text = songData[position].songField!![0]
    }
}