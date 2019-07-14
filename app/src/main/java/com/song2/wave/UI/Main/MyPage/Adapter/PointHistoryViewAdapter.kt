package com.song2.wave.UI.Main.MyPage.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.song2.wave.Data.model.MyPage.PointHistoryData
import com.song2.wave.R
import com.song2.wave.UI.Main.MainActivity


class PointHistoryViewAdapter(val ctx : Context, val dataList : ArrayList<PointHistoryData>) : RecyclerView.Adapter<PointHistoryViewAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view : View = LayoutInflater.from(ctx).inflate(R.layout.item_point_history, parent, false)
        return Holder(view)
    }
    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        if(dataList[position].is500 == true){
            //holder.is100.visibility.
            holder.is100.visibility = View.GONE
            holder.is500.visibility = View.VISIBLE
        }else {
            holder.is500.visibility = View.GONE
            holder.is100.visibility = View.VISIBLE
        }

        holder.cover_title.text = dataList[position].cover_title
        holder.cover_artist.text = dataList[position].cover_artist
        holder.point.text = dataList[position].point.toString()

        /*
        holder.item_btn.setOnClickListener {
            ctx.toast("메인으로")
            ctx.startActivity<MainActivity>()
        }*/
    }

    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val is500 : ImageView = itemView.findViewById(R.id.iv_point_history_item_image_500) as ImageView
        val is100 : ImageView = itemView.findViewById(R.id.iv_point_history_item_image_100) as ImageView
        val cover_title : TextView = itemView.findViewById(R.id.tv_point_history_item_title) as TextView
        val cover_artist : TextView = itemView.findViewById(R.id.tv_point_history_item_artist) as TextView
        val point : TextView = itemView.findViewById(R.id.tv_point_history_item_cnt) as TextView

        //val item_btn : RelativeLayout = itemView.findViewById(R.id.bt_rv_point_history_item) as RelativeLayout
    }
}