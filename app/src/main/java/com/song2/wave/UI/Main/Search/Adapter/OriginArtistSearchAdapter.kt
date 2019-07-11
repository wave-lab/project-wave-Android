package com.song2.wave.UI.Main.Search.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.song2.wave.Data.model.CoverArtistData
import com.song2.wave.Data.model.OriginArtistData
import com.song2.wave.R

class OriginArtistSearchAdapter (private var originArtistData: ArrayList<OriginArtistData>, var requestManager : RequestManager) : RecyclerView.Adapter<OriginArtistSearchViewHolder>(){

    var EXAMPLE_IMG_URL : String = "https://t1.daumcdn.net/cfile/tistory/2641FF4C5900DDDE1E"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OriginArtistSearchViewHolder {
        val mainView : View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_cover_artist_view, parent, false)
        return OriginArtistSearchViewHolder(mainView)
    }

    override fun getItemCount(): Int = originArtistData.size

    override fun onBindViewHolder(holder: OriginArtistSearchViewHolder, position: Int) {

        requestManager.load(originArtistData[position].originArtistImg).centerCrop().into(holder.originArtistImg)
        // ex)
        //requestManager.load(EXAMPLE_IMG_URL).into(holder.coverArtistImg)
        holder.originArtistName.text = originArtistData[position].originArtistName
    }
}