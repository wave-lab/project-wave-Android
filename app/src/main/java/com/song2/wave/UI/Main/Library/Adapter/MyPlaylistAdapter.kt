package com.song2.wave.UI.Main.Library.Adapter


import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.RequestManager
import com.song2.wave.Data.model.PlaylistListData
import com.song2.wave.R

class MyPlaylistAdapter (private var songData : ArrayList<PlaylistListData>, var requestManager : RequestManager) : RecyclerView.Adapter<SongPlayListViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongPlayListViewHolder {
        val mainView : View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_artist_playlist, parent, false)
        return SongPlayListViewHolder(mainView)
    }

    override fun getItemCount(): Int = songData.size

    override fun onBindViewHolder(holder: SongPlayListViewHolder, position: Int) {

        var thumbList : ArrayList<ImageView> = arrayListOf(holder.playListImg0,holder.playListImg1,holder.playListImg2, holder.playListImg3)

        for (i in songData[position].imgList!!.indices)
            requestManager.load(songData[position].imgList!![i]).centerCrop().into(thumbList[i])
        Log.e("imgList size",songData[position].imgList!!.indices.toString())
        // ex)
        //requestManager.load(EXAMPLE_IMG_URL).into(holder.songCoverImg)
        holder.playListName.text = songData[position].playListName
        holder.playListComment.text = songData[position].playListComment

        holder.playListMoreBtn.setOnClickListener {

        }
    }
}