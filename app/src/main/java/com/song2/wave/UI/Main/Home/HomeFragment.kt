package com.song2.wave.UI.Main.Home

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.song2.wave.Data.model.Home.HomeSongData
import com.song2.wave.Data.model.Home.MyWaitingSongData
import com.song2.wave.R
import com.song2.wave.UI.Main.Home.Adapter.HitSongHomeAdapter
import com.song2.wave.UI.Main.Home.Adapter.MyWaitingSongHomeAdapter
import com.song2.wave.UI.Main.Home.Adapter.RecomentSongHomeAdapter
import com.song2.wave.UI.Main.Home.Adapter.WaitingSongHomeAdapter
import com.song2.wave.UI.MainPlayer.MainPlayerActivity
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    lateinit var myWaitingSongDataList: ArrayList<MyWaitingSongData>
    lateinit var waitingSongDataList: ArrayList<HomeSongData>
    lateinit var hitSongHomeDataList: ArrayList<HomeSongData>
    lateinit var recommendSongHomeDataList: ArrayList<HomeSongData>

    lateinit var myWaitingSongHomeAdapter: MyWaitingSongHomeAdapter
    lateinit var waitingSongHomeAdapter: WaitingSongHomeAdapter
    lateinit var hitSongHomeAdapter: HitSongHomeAdapter
    lateinit var recommendSongHomeAdapter: RecomentSongHomeAdapter


    lateinit var requestManager: RequestManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v : View = inflater.inflate(R.layout.fragment_home, container, false)

        v.iv_home_frag_wavelogo.setOnClickListener {
            var intent = Intent(context, MainPlayerActivity::class.java)
            startActivity(intent)
        }

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        attachRecyclerView()


    }

    fun attachRecyclerView(){


        waitingSongDataList = ArrayList()
        myWaitingSongDataList = ArrayList()
        hitSongHomeDataList = ArrayList()
        recommendSongHomeDataList = ArrayList()

        requestManager = Glide.with(this)

        insertExampleData()

        recommendSongHomeAdapter = RecomentSongHomeAdapter( recommendSongHomeDataList, requestManager)
        rv_home_frag_scoring_recommend_list.adapter = recommendSongHomeAdapter
        rv_home_frag_scoring_recommend_list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)


        hitSongHomeAdapter = HitSongHomeAdapter( hitSongHomeDataList, requestManager)
        rv_home_frag_scoring_hit_list.adapter = hitSongHomeAdapter
        rv_home_frag_scoring_hit_list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        waitingSongHomeAdapter = WaitingSongHomeAdapter(waitingSongDataList, requestManager)
        rv_home_frag_scoring_waiting.adapter = waitingSongHomeAdapter
        rv_home_frag_scoring_waiting.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        myWaitingSongHomeAdapter = MyWaitingSongHomeAdapter(myWaitingSongDataList, requestManager)
        rv_home_frag_scoring_waiting_mine.adapter = myWaitingSongHomeAdapter
        rv_home_frag_scoring_waiting_mine.layoutManager =  LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    fun insertExampleData() {
        recommendSongHomeDataList.add(HomeSongData("https://images.otwojob.com/product/E/a/n/EandNVOq2rIbOu0.png/o2j/resize/900%3E", "똥꼬1", "류지훈", "양승희"))
        recommendSongHomeDataList.add(HomeSongData("https://images.otwojob.com/product/E/a/n/EandNVOq2rIbOu0.png/o2j/resize/900%3E", "똥꼬1", "류지훈", "양승희"))
        recommendSongHomeDataList.add(HomeSongData("https://images.otwojob.com/product/E/a/n/EandNVOq2rIbOu0.png/o2j/resize/900%3E", "똥꼬1", "류지훈", "양승희"))
        recommendSongHomeDataList.add(HomeSongData("https://images.otwojob.com/product/E/a/n/EandNVOq2rIbOu0.png/o2j/resize/900%3E", "똥꼬1", "류지훈", "양승희"))
        recommendSongHomeDataList.add(HomeSongData("https://images.otwojob.com/product/E/a/n/EandNVOq2rIbOu0.png/o2j/resize/900%3E", "똥꼬1", "류지훈", "양승희"))
        recommendSongHomeDataList.add(HomeSongData("https://images.otwojob.com/product/E/a/n/EandNVOq2rIbOu0.png/o2j/resize/900%3E", "똥꼬1", "류지훈", "양승희"))


        hitSongHomeDataList.add(HomeSongData("https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "똥꼬1", "류지훈", "양승희"))
        hitSongHomeDataList.add(HomeSongData("https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "똥꼬1", "류지훈", "양승희"))
        hitSongHomeDataList.add(HomeSongData("https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "똥꼬1", "류지훈", "양승희"))
        hitSongHomeDataList.add(HomeSongData("https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "똥꼬1", "류지훈", "양승희"))
        hitSongHomeDataList.add(HomeSongData("https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "똥꼬1", "류지훈", "양승희"))
        hitSongHomeDataList.add(HomeSongData("https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "똥꼬1", "류지훈", "양승희"))

        waitingSongDataList.add(HomeSongData("https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "똥꼬1", "류지훈", "양승희"))
        waitingSongDataList.add(HomeSongData("https://images.otwojob.com/product/E/a/n/EandNVOq2rIbOu0.png/o2j/resize/900%3E", "똥꼬2", "류지훈", "양승희"))
        waitingSongDataList.add(HomeSongData("https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "똥꼬3", "류지훈", "양승희"))
        waitingSongDataList.add(HomeSongData("https://images.otwojob.com/product/E/a/n/EandNVOq2rIbOu0.png/o2j/resize/900%3E", "똥꼬4", "류지훈", "양승희"))
        waitingSongDataList.add(HomeSongData("https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "똥꼬5", "류지훈", "양승희"))

        myWaitingSongDataList.add(MyWaitingSongData(1, "https://images.otwojob.com/product/x/U/6/xU6PzuxMzIFfSQ9.jpg/o2j/resize/852x622%3E", "노래제목1", "가숫1"))
        myWaitingSongDataList.add(MyWaitingSongData(2, "https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "노래제목2", "가숫2"))
        myWaitingSongDataList.add(MyWaitingSongData(3, "https://images.otwojob.com/product/E/a/n/EandNVOq2rIbOu0.png/o2j/resize/900%3E", "노래제목3", "가숫3"))
        myWaitingSongDataList.add(MyWaitingSongData(4, "https://images.otwojob.com/product/x/U/6/xU6PzuxMzIFfSQ9.jpg/o2j/resize/852x622%3E", "노래제목1", "가숫1"))
        myWaitingSongDataList.add(MyWaitingSongData(5, "https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E", "노래제목2", "가숫2"))
        myWaitingSongDataList.add(MyWaitingSongData(6, "https://images.otwojob.com/product/E/a/n/EandNVOq2rIbOu0.png/o2j/resize/900%3E", "노래제목3", "가숫3"))
    }
}