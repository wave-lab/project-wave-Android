package com.song2.wave.UI.Artist.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.song2.wave.Data.model.ArtistPlaylistSongData
import com.song2.wave.R

class ArtistPlaylistAdapter(val ctx: Context, val datalist: ArrayList<ArtistPlaylistSongData>) :
    RecyclerView.Adapter<ArtistPlaylistAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.item_artist_playlist, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = datalist.size

    override fun onBindViewHolder(holder: Holder, position: Int) {


        holder.playlistName.text = datalist[position].playlistName
        holder.comment.text = datalist[position].comment

        inserImg(datalist[position].songCoverImg, holder, position)

    }

    fun inserImg(arrayList: ArrayList<String>, holder: Holder, position: Int) {

        var holderImgList = arrayListOf(
            holder.songFirCoverImg,
            holder.songSecCoverImg,
            holder.songThirCoverImg,
            holder.songFourCoverImg
        )


        for (i in 0 until 4) {
            Glide.with(ctx)
                .load(datalist[position].songCoverImg[i])
                .into(holderImgList[i])
        }

    }

    inner class Holder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        var songFirCoverImg = itemView.findViewById(R.id.img_one) as ImageView
        var songSecCoverImg = itemView.findViewById(R.id.img_two) as ImageView
        var songThirCoverImg = itemView.findViewById(R.id.img_three) as ImageView
        var songFourCoverImg = itemView.findViewById(R.id.img_four) as ImageView
        var playlistName = itemView.findViewById(R.id.tv_artist_playlist_name) as TextView
        var comment = itemView.findViewById(R.id.tv_artist_comment) as TextView
    }

}