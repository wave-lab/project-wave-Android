package com.song2.wave.UI.Main.MyPage.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.song2.wave.Data.model.Mypage.ScoreResultData
import com.song2.wave.R

class ScoreResultAdapter(private var resultSongData: ArrayList<ScoreResultData>, var requestManager : RequestManager) : RecyclerView.Adapter<ScoreResultViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreResultViewHolder {
        val mainView : View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_scoring_result_song, parent, false)
        return ScoreResultViewHolder(mainView)
    }

    override fun getItemCount(): Int = resultSongData.size

    override fun onBindViewHolder(holder: ScoreResultViewHolder, position: Int) {
        requestManager.load(resultSongData[position].songCoverImg).into(holder.songCoverImg)
        holder.songOriginInfo.text = resultSongData[position].songName +" - "+ resultSongData[position].songOriginArtist
        holder.songField.text = resultSongData[position].songFiled[0]
        holder.songResultScore.text = "통과점수 : " +  resultSongData[position].songScore.toString()
    }
}