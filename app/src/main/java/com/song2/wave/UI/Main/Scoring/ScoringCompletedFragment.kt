package com.song2.wave.UI.Main.Scoring

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.song2.wave.Data.model.Scoring.PassedSongData
import com.song2.wave.R
import com.song2.wave.UI.Main.Scoring.Adapter.ScoringPassedRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_scoring_completed.*


class ScoringCompletedFragment : Fragment(){

    lateinit var scoringPassedRecyclerViewAdapter : ScoringPassedRecyclerViewAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_scoring_completed,container,false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
// 서버 통신
        var dataList: ArrayList<PassedSongData> = ArrayList()
        dataList.add(PassedSongData(
            "33.png","Song name is...","Cover Artist"))
        dataList.add(PassedSongData(
            "33.png","Song name is...","Cover Artist"))
        dataList.add(PassedSongData(
            "33.png","Song name is...","Cover Artist"))

        scoringPassedRecyclerViewAdapter = ScoringPassedRecyclerViewAdapter(context!!, dataList)
        rv_scoring_song_passed.adapter = scoringPassedRecyclerViewAdapter
        rv_scoring_song_passed.layoutManager = GridLayoutManager(context!!, 3)


    }

}