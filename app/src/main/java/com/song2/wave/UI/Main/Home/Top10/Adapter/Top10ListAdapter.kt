package com.song2.wave.UI.Main.Home.Top10.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.song2.wave.Data.model.Home.Top10ListData
import com.song2.wave.R

class Top10ListAdapter(private var top10dListdata: ArrayList<Top10ListData>, var requestManager: RequestManager) :
    RecyclerView.Adapter<Top10ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Top10ListViewHolder {
        val mainView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_chart_song, parent, false)
        return Top10ListViewHolder(mainView)
    }

    override fun getItemCount(): Int = top10dListdata.size

    override fun onBindViewHolder(holder: Top10ListViewHolder, position: Int) {

        requestManager.load(top10dListdata[position].topSongCoverImg).into(holder.top10Img)

        holder.top10Rank.text = top10dListdata[position].topRank.toString()
        holder.top10OriginSongInfo.text = top10dListdata[position].topSongName + "- " + top10dListdata[position].topOriginArtistName
        holder.top10CoverArtist.text = top10dListdata[position].topCoverArtistName
        holder.top10SongField.text = top10dListdata[position].topSongField[0]
    }

}