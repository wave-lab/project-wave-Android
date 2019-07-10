package com.song2.wave.UI.Main.Home.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.song2.wave.Data.model.Home.TOP10Data
import com.song2.wave.R

class Top10MoodAdapter(private var top10data: ArrayList<TOP10Data>, var requestManager : RequestManager) : RecyclerView.Adapter<Top10ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Top10ViewHolder {
        val mainView : View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_top10, parent, false)
        return Top10ViewHolder(mainView)
    }

    override fun getItemCount(): Int = top10data.size

    override fun onBindViewHolder(holder: Top10ViewHolder, position: Int) {
        requestManager.load(top10data[position].top10Thumbnail).into(holder.top10CoverImg)
        holder.top10Kinds.text = top10data[position].top10Name

    }
}