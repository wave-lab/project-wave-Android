package com.song2.wave.UI.Main.Search.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.song2.wave.Data.model.CoverArtistData
import com.song2.wave.R

class CoverArtistSearchAdapter (private var coverArtistData : ArrayList<CoverArtistData>, var requestManager : RequestManager) : RecyclerView.Adapter<CoverArtistSearchViewHolder>(){

    var EXAMPLE_IMG_URL : String = "https://t1.daumcdn.net/cfile/tistory/2641FF4C5900DDDE1E"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoverArtistSearchViewHolder {
        val mainView : View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_cover_artist_view, parent, false)
        return CoverArtistSearchViewHolder(mainView)
    }

    override fun getItemCount(): Int = coverArtistData.size

    override fun onBindViewHolder(holder: CoverArtistSearchViewHolder, position: Int) {

        requestManager.load(coverArtistData[position].coverArtistImg).centerCrop().into(holder.coverArtistImg)
        // ex)
        //requestManager.load(EXAMPLE_IMG_URL).into(holder.coverArtistImg)
        holder.coverArtistName.text = coverArtistData[position].coverArtistName
    }
}