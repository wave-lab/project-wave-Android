package com.song2.wave.UI.Main.Search.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.song2.wave.Data.model.SearchArtistData
import com.song2.wave.R


//Adapter를 통해 view와 data를 연결시킨다.
class SearchArtistViewAdapter(val ctx : Context, val dataList : ArrayList<SearchArtistData>) : RecyclerView.Adapter<SearchArtistViewAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder{
        //뷰 인플레이트
        var view : View = LayoutInflater.from(ctx).inflate(R.layout.item_search_origin_artist, parent, false)
        return Holder(view)
    }
    override fun getItemCount() : Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        Glide.with(ctx).load(dataList[position].image).into(holder.image)
        holder.title.text = dataList[position].title

        holder.item_btn.setOnClickListener {

        }
    }

    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val image : ImageView = itemView.findViewById(R.id.iv_search_origin_artist_item_image) as ImageView
        val title : TextView = itemView.findViewById(R.id.tv_search_origin_artist_item_title) as TextView

        val item_btn : RelativeLayout = itemView.findViewById(R.id.bt_rv_item_search_origin_artist) as RelativeLayout
    }
}