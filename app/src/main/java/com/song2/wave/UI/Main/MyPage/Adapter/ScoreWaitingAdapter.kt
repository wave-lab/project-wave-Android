package com.song2.wave.UI.Main.MyPage.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.song2.wave.Data.model.Mypage.ScoreWaitingData
import com.song2.wave.R

class ScoreWaitingAdapter(private var resultSongData: ArrayList<ScoreWaitingData>, var requestManager : RequestManager) : RecyclerView.Adapter<ScoreWaitingViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreWaitingViewHolder {
        val mainView : View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_scoring_result_waiting, parent, false)
        return ScoreWaitingViewHolder(mainView)
    }

    override fun getItemCount(): Int = resultSongData.size

    override fun onBindViewHolder(holder: ScoreWaitingViewHolder, position: Int) {
        requestManager.load(resultSongData[position].songCoverImg).into(holder.songCoverImg
        )
        holder.songOriginInfo.text = resultSongData[position].songName +" - "+ resultSongData[position].songArtist
        holder.songField.text = resultSongData[position].songField[0]

    }
}