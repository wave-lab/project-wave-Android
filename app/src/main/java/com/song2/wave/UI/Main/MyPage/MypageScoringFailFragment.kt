package com.song2.wave.UI.Main.MyPage

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.song2.wave.Data.model.Mypage.ScoreResultData
import com.song2.wave.R
import com.song2.wave.UI.Main.MyPage.Adapter.ScoreResultAdapter
import kotlinx.android.synthetic.main.fragment_mypage_scoring_fail.*

class MypageScoringFailFragment : Fragment(){

    lateinit var failedScoreResultData: ArrayList<ScoreResultData>
    lateinit var failedScoreResultAdapter: ScoreResultAdapter
    lateinit var requestManager: RequestManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_mypage_scoring_fail,container,false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        attachRecyclerView()
    }

    fun attachRecyclerView() {
        failedScoreResultData = ArrayList()

        requestManager = Glide.with(this)

        insertExampleData()

        failedScoreResultAdapter = ScoreResultAdapter(failedScoreResultData, requestManager)
        rv_mypage_frag_score_failed_list.adapter = failedScoreResultAdapter
        rv_mypage_frag_score_failed_list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)


    }

    fun insertExampleData() {
        failedScoreResultData.add(ScoreResultData(3,"https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E","똥꼬", "류지훈", arrayListOf("힙", "합")))
        failedScoreResultData.add(ScoreResultData(3,"https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E","똥꼬", "류지훈", arrayListOf("힙", "합")))
        failedScoreResultData.add(ScoreResultData(3,"https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E","똥꼬", "류지훈", arrayListOf("힙", "합")))
        failedScoreResultData.add(ScoreResultData(3,"https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E","똥꼬", "류지훈", arrayListOf("힙", "합")))
        failedScoreResultData.add(ScoreResultData(3,"https://images.otwojob.com/product/P/o/M/PoM0Lnkz9z54kZS.png/o2j/resize/900%3E","똥꼬", "류지훈", arrayListOf("힙", "합")))

    }
}