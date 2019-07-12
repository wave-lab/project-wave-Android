package com.song2.wave.UI.Main.Home.Adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.song2.wave.Util.Audio.AudioApplication

import com.song2.wave.Data.model.Home.HomeSongData
import com.song2.wave.R
import com.song2.wave.UI.MainPlayer.MainPlayerActivity


class WaitingSongHomeAdapter (context : Context, private var waitingSongData: ArrayList<HomeSongData>, var requestManager : RequestManager) : RecyclerView.Adapter<WaitingSongHomeViewHolder>(){

    var mContext : Context = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WaitingSongHomeViewHolder {
        val mainView : View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_waiting_song, parent, false)
        return WaitingSongHomeViewHolder(mainView)
    }


    override fun getItemCount(): Int = waitingSongData.size

    override fun onBindViewHolder(holder: WaitingSongHomeViewHolder, position: Int) {

        requestManager.load(waitingSongData[position].songCoverImg).into(holder.songCoverImg)
        holder.songInfo.text = waitingSongData[position].songName +" - "+ waitingSongData[position].originArtistName
        Log.v("ASdf","테스트 = " +  waitingSongData[position].originArtistName)
        holder.artistname.text = waitingSongData[position].coverArtistName

        holder.itemView.setOnClickListener{
            var intent = Intent(mContext, MainPlayerActivity::class.java)
            //AudioApplication.getInstance().serviceInterface.setPlayList(holder.adapterPosition) // 재생목록등록
            AudioApplication.getInstance().serviceInterface.play(waitingSongData[holder.adapterPosition]._id, waitingSongData[holder.adapterPosition].songUrl, waitingSongData[position].originArtistName, waitingSongData[position].coverArtistName, waitingSongData[position].songName) // 선택한 오디오재생
            intent.putExtra("_id", waitingSongData[holder.adapterPosition]._id)
            Log.v("ASdf","테스트2 id = " + waitingSongData[holder.adapterPosition]._id)
            intent.putExtra("songUrl", waitingSongData[holder.adapterPosition].songUrl)
            intent.putExtra("title", waitingSongData[holder.adapterPosition].songName)
            intent.putExtra("originArtist", waitingSongData[holder.adapterPosition].originArtistName)
            intent.putExtra("coverArtist", waitingSongData[holder.adapterPosition].coverArtistName)
            mContext.startActivity(intent)
        }

    }
}
