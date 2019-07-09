package com.song2.wave.UI.Main.MyPage.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.song2.wave.Data.model.Mypage.ScoreHitResultData
import com.song2.wave.R


class HitScoreResultAdapter(private var scoreHitResultData: ArrayList<ScoreHitResultData>, var requestManager : RequestManager) : RecyclerView.Adapter<HitScoreResultViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HitScoreResultViewHolder {
        val mainView : View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_score_hit_result, parent, false)
        return HitScoreResultViewHolder(mainView)
    }

    override fun getItemCount(): Int = scoreHitResultData.size

    override fun onBindViewHolder(holder: HitScoreResultViewHolder, position: Int) {


        if(scoreHitResultData[position].isPassed){
            holder.failFilter.visibility = View.GONE
            holder.passFilter.visibility = View.VISIBLE
        }else if(!scoreHitResultData[position].isPassed)
        {
            holder.failFilter.visibility = View.VISIBLE
            holder.passFilter.visibility = View.GONE
        }

        requestManager.load(scoreHitResultData[position].songCoverImg).into(holder.songCoverImg)
        holder.songInfo.text = scoreHitResultData[position].songName +" - "+ scoreHitResultData[position].songArtist
        holder.coverArtistname.text = scoreHitResultData[position].coverArtist
    }
}