package com.song2.wave.UI.Main.Home.Adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.song2.wave.AudioTest.AudioApplication
import com.song2.wave.Data.model.Home.TOP10Data
import com.song2.wave.R
import com.song2.wave.UI.MainPlayer.MainPlayerActivity
import com.song2.wave.UI.Main.Home.HomeFragment
import com.song2.wave.UI.Main.Home.Top10.Top10Fragment


class Top10GenreAdapter(var context : Context, private var top10data: ArrayList<TOP10Data>, var requestManager : RequestManager) : RecyclerView.Adapter<Top10ViewHolder>(){

    var mContext = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Top10ViewHolder {
        val mainView : View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_top10, parent, false)
        return Top10ViewHolder(mainView)
    }

    override fun getItemCount(): Int = top10data.size

    override fun onBindViewHolder(holder: Top10ViewHolder, position: Int) {
        requestManager.load(top10data[position].top10Thumbnail).into(holder.top10CoverImg)
        holder.top10Kinds.text = top10data[position].top10Name


            holder.top10ItemContainer.setOnClickListener {

                /*            bundleMood.putStringArrayList("top10Rank",moodRank)
                            top10fragment.setArguments(bundleMood)*/

                var bundle = Bundle()
                val top10frag = Top10Fragment()

                Log.e("top10data_id", top10data[position]._id)

                bundle.putString("genreId",top10data[position]._id)
                top10frag.setArguments(bundle)

                HomeFragment.homeFragment.replaceFragment(top10frag)
        }

    }
}