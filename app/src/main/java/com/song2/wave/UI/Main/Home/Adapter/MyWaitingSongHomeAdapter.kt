package com.song2.wave.UI.Main.Home.Adapter

import android.content.Context
import android.content.Intent
import android.support.annotation.IntegerRes
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.song2.wave.Util.Audio.AudioApplication
import com.song2.wave.Data.model.Home.MyWaitingSongData
import com.song2.wave.R
import com.song2.wave.UI.MainPlayer.MainPlayerActivity
import org.jetbrains.anko.db.INTEGER
import java.text.SimpleDateFormat
import java.util.*

class MyWaitingSongHomeAdapter (context : Context, private var waitingSongData: ArrayList<MyWaitingSongData>, var requestManager : RequestManager) : RecyclerView.Adapter<MyWaitingSongHomeViewHolder>(){

    var mContext = context
    var format1 = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    var time = Date()

    var time1 = format1.format(time)
    var currentData = time1.substring(8, 10)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyWaitingSongHomeViewHolder {
        val mainView : View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_my_waiting_song, parent, false)
        return MyWaitingSongHomeViewHolder(mainView)
    }

    override fun getItemCount(): Int = waitingSongData.size

    override fun onBindViewHolder(holder: MyWaitingSongHomeViewHolder, position: Int) {

        Log.v("Asdf", "현재 날짜 = " + currentData)
        var currentDateNum : Int = Integer.parseInt(currentData)
        var deleteDateNum : Int = Integer.parseInt(waitingSongData[position].songWaitingDay_mine)
        requestManager.load(waitingSongData[position].songCoverImg_mine).into(holder.songCoverImg)
        holder.songInfo.text = waitingSongData[position].songName_mine +" - "+ waitingSongData[position].originArtistName_mine
        holder.songDDay.text = "D - "+ (deleteDateNum - currentDateNum).toString()

        holder.itemView.setOnClickListener{
            var intent = Intent(mContext, MainPlayerActivity::class.java)
            //AudioApplication.getInstance().serviceInterface.setPlayList(holder.adapterPosition) // 재생목록등록
            AudioApplication.getInstance().serviceInterface.play(mContext, waitingSongData[holder.adapterPosition].songId, waitingSongData[holder.adapterPosition].songUrl, waitingSongData[position].originArtistName_mine, waitingSongData[position].coverArtistName, waitingSongData[position].songName_mine!!, 0) // 선택한 오디오재생
            intent.putExtra("_id", waitingSongData[holder.adapterPosition].songId)
            Log.v("ASdf","테스트1 id = " + waitingSongData[holder.adapterPosition].songId)
            intent.putExtra("songUrl", waitingSongData[holder.adapterPosition].songUrl)
            intent.putExtra("title", waitingSongData[holder.adapterPosition].songName_mine)
            intent.putExtra("originArtist", waitingSongData[holder.adapterPosition].originArtistName_mine)
            intent.putExtra("coverArtist", waitingSongData[holder.adapterPosition].coverArtistName)
            intent.putExtra("songImgUrl", waitingSongData[holder.adapterPosition].songCoverImg_mine)
            intent.putExtra("rating_flag", 0)
            mContext.startActivity(intent)
        }
    }
}