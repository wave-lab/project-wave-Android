package com.song2.wave.UI.Main.MyPage

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.song2.wave.Data.model.Mypage.ScoreWaitingData
import com.song2.wave.R
import com.song2.wave.UI.Main.MyPage.Adapter.ScoreWaitingAdapter
import kotlinx.android.synthetic.main.fragment_mypage_scoring_waiting.*

class MypageScoringWaitingFragment : Fragment(){

    lateinit var scoreWaitingData : ArrayList<ScoreWaitingData>
    lateinit var scoreWaitingAdapter : ScoreWaitingAdapter
    lateinit var requestManager: RequestManager


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_mypage_scoring_waiting,container,false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        attachRecyclerView()
    }

    fun attachRecyclerView() {
        scoreWaitingData = ArrayList()

        requestManager = Glide.with(this)

        insertExampleData()

        scoreWaitingAdapter = ScoreWaitingAdapter(scoreWaitingData, requestManager)
        rv_mypage_frag_score_waiting_list.adapter = scoreWaitingAdapter
        rv_mypage_frag_score_waiting_list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)


    }

    fun insertExampleData() {
        scoreWaitingData.add(ScoreWaitingData("https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E","똥꼬","류지훈", arrayListOf("힙", "합")))
        scoreWaitingData.add(ScoreWaitingData("https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E","똥꼬","류지훈", arrayListOf("힙", "합")))
        scoreWaitingData.add(ScoreWaitingData("https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E","똥꼬","류지훈", arrayListOf("힙", "합")))
        scoreWaitingData.add(ScoreWaitingData("https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E","똥꼬","류지훈", arrayListOf("힙", "합")))
        scoreWaitingData.add(ScoreWaitingData("https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E","똥꼬","류지훈", arrayListOf("힙", "합")))

    }
}