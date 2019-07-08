package com.song2.wave.UI.Main.MyPage.Adapter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.song2.wave.Data.model.MyPage.HitSuccessSongData
import com.song2.wave.R
import kotlinx.android.synthetic.main.fragment_my_page_hit_success.*

class MyPageHitSuccessFragment  :  Fragment(){
    lateinit var requestManager: RequestManager
    lateinit var songFieldData: ArrayList<String>

    lateinit var myPageHitSuccessAdapter: MyPageHitSuccessAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_my_page_hit_success,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

// 서버 통신

        var hitsuccess: ArrayList<HitSuccessSongData> = ArrayList()
        songFieldData = ArrayList<String>()
        songFieldData.add("장르")

        hitsuccess.add(HitSuccessSongData(
            "https://upload.wikimedia.org/wikipedia/ko/thumb/f/fe/방탄소년단_MAP_OF_THE_SOUL_-_PERSONA.png/220px-방탄소년단_MAP_OF_THE_SOUL_-_PERSONA.png",
            "WAVE","이성은","떵으니",songFieldData,
            4.2,4))
        hitsuccess.add(HitSuccessSongData(
            "https://upload.wikimedia.org/wikipedia/ko/thumb/f/fe/방탄소년단_MAP_OF_THE_SOUL_-_PERSONA.png/220px-방탄소년단_MAP_OF_THE_SOUL_-_PERSONA.png",
            "WAVE","이성은","떵으니",songFieldData,
            3.3,3))
        hitsuccess.add(HitSuccessSongData(
            "https://upload.wikimedia.org/wikipedia/ko/thumb/f/fe/방탄소년단_MAP_OF_THE_SOUL_-_PERSONA.png/220px-방탄소년단_MAP_OF_THE_SOUL_-_PERSONA.png",
            "WAVE","이성은","떵으니",songFieldData,
            1.2,1))
        hitsuccess.add(HitSuccessSongData(
            "https://upload.wikimedia.org/wikipedia/ko/thumb/f/fe/방탄소년단_MAP_OF_THE_SOUL_-_PERSONA.png/220px-방탄소년단_MAP_OF_THE_SOUL_-_PERSONA.png",
            "WAVE","이성은","떵으니",songFieldData,
            5.0,5))
        hitsuccess.add(HitSuccessSongData(
            "https://upload.wikimedia.org/wikipedia/ko/thumb/f/fe/방탄소년단_MAP_OF_THE_SOUL_-_PERSONA.png/220px-방탄소년단_MAP_OF_THE_SOUL_-_PERSONA.png",
            "WAVE","이성은","떵으니",songFieldData,
            2.3,2))
        hitsuccess.add(HitSuccessSongData(
            "https://upload.wikimedia.org/wikipedia/ko/thumb/f/fe/방탄소년단_MAP_OF_THE_SOUL_-_PERSONA.png/220px-방탄소년단_MAP_OF_THE_SOUL_-_PERSONA.png",
            "WAVE","이성은","떵으니",songFieldData,
            4.1,4))
        hitsuccess.add(HitSuccessSongData(
            "https://upload.wikimedia.org/wikipedia/ko/thumb/f/fe/방탄소년단_MAP_OF_THE_SOUL_-_PERSONA.png/220px-방탄소년단_MAP_OF_THE_SOUL_-_PERSONA.png",
            "WAVE","이성은","떵으니",songFieldData,
            3.0,3))


        myPageHitSuccessAdapter = MyPageHitSuccessAdapter(context!!, hitsuccess)
        rv_my_page_hit_success.adapter = myPageHitSuccessAdapter
        rv_my_page_hit_success.layoutManager = LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)
    }
}
