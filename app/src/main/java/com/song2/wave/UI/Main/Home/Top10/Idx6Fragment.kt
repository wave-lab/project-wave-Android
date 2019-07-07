package com.song2.wave.UI.Main.Home.Top10

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.song2.wave.Data.model.Home.Top10ListData
import com.song2.wave.UI.Main.Home.Top10.Adapter.Top10ListAdapter
import kotlinx.android.synthetic.main.fragment_top10_idx6.*

class Idx6Fragment : Fragment(){


    lateinit var top10Idx6List: ArrayList<Top10ListData>
    lateinit var top10Idx6ListAdapter: Top10ListAdapter

    lateinit var requestManager: RequestManager


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v: View = inflater.inflate(com.song2.wave.R.layout.fragment_top10_idx6, container, false)

        return v
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        attachRecyclerView()
    }

    fun attachRecyclerView(){

        top10Idx6List = ArrayList()
        requestManager = Glide.with(this)

        insertData()

        top10Idx6ListAdapter = Top10ListAdapter(top10Idx6List, requestManager)
        rv_top10_idx6_list.adapter = top10Idx6ListAdapter
        rv_top10_idx6_list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

    }

    fun insertData(){

        top10Idx6List.add(Top10ListData(2, "https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "똥꼬1", "류지훈", "양승희", arrayListOf("힙합","댄수")))
        top10Idx6List.add(Top10ListData(3, "https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "똥꼬1", "류지훈", "양승희", arrayListOf("힙합","댄수")))
        top10Idx6List.add(Top10ListData(4, "https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "똥꼬1", "류지훈", "양승희", arrayListOf("힙합","댄수")))
        top10Idx6List.add(Top10ListData(5, "https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "똥꼬1", "류지훈", "양승희", arrayListOf("힙합","댄수")))
        top10Idx6List.add(Top10ListData(6, "https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "똥꼬1", "류지훈", "양승희", arrayListOf("힙합","댄수")))

    }
}