package com.song2.wave.UI.Signup

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.song2.wave.Data.model.RealArtistData
import com.song2.wave.R
import com.song2.wave.UI.Main.Search.Adapter.CoverArtistSearchViewHolder

class SignupArtistAdapter (private var realArtistData : ArrayList<RealArtistData>, var requestManager: RequestManager) : RecyclerView.Adapter<SignupArtistViewHolder>(){

    var EXAMPLE_IMG_URL : String = "https://t1.daumcdn.net/cfile/tistory/2641FF4C5900DDDE1E"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SignupArtistViewHolder {
        val mainView : View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_artist_select, parent, false)
        return SignupArtistViewHolder(mainView)
    }

    override fun getItemCount(): Int = realArtistData.size

    override fun onBindViewHolder(holder: SignupArtistViewHolder, position: Int) {

        //requestManager.load(EXAMPLE_IMG_URL).into(holder.realArtistImg)
        holder.realArtistImg.setOnClickListener {
            requestManager.load(EXAMPLE_IMG_URL).into(holder.realArtistImg)
        }
        holder.realArtistName.text = realArtistData[position].realArtistName
    }
}