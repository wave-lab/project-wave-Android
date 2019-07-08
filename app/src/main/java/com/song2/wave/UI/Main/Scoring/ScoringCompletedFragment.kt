package com.song2.wave.UI.Main.Scoring

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import com.song2.wave.Data.model.Home.WaitingSongData
import com.song2.wave.Data.model.Scoring.PassedCompletedSongData
import com.song2.wave.Data.model.Scoring.PassedSongData
import com.song2.wave.Data.model.SongData
import com.song2.wave.R
import com.song2.wave.UI.Main.Scoring.Adapter.ScoringFailedRecyclerViewAdapter
import com.song2.wave.UI.Main.Scoring.Adapter.ScoringPassedListRecyclerViewAdapter
import com.song2.wave.UI.Main.Scoring.Adapter.ScoringPassedRecyclerViewAdapter
import com.song2.wave.UI.Main.Scoring.Adapter.ScoringPassingRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_scoring_passed.*
import kotlinx.android.synthetic.main.fragment_scoring_completed.*


class ScoringCompletedFragment : Fragment(){

    lateinit var scoringPassedRecyclerViewAdapter : ScoringPassedRecyclerViewAdapter
    lateinit var scoringPassingRecyclerViewAdapter : ScoringPassingRecyclerViewAdapter
    lateinit var scoringFailedRecyclerViewAdapter : ScoringFailedRecyclerViewAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_scoring_completed,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

// 서버 통신
        var dataListPassed: ArrayList<PassedCompletedSongData> = ArrayList()
        dataListPassed.add(PassedCompletedSongData(
            "https://upload.wikimedia.org/wikipedia/ko/thumb/f/fe/방탄소년단_MAP_OF_THE_SOUL_-_PERSONA.png/220px-방탄소년단_MAP_OF_THE_SOUL_-_PERSONA.png","WAVE","이성은"))
        dataListPassed.add(PassedCompletedSongData(
            "https://upload.wikimedia.org/wikipedia/ko/thumb/f/fe/방탄소년단_MAP_OF_THE_SOUL_-_PERSONA.png/220px-방탄소년단_MAP_OF_THE_SOUL_-_PERSONA.png","WAVE","이성은"))
        dataListPassed.add(PassedCompletedSongData(
            "https://upload.wikimedia.org/wikipedia/ko/thumb/f/fe/방탄소년단_MAP_OF_THE_SOUL_-_PERSONA.png/220px-방탄소년단_MAP_OF_THE_SOUL_-_PERSONA.png","WAVE","이성은"))
        dataListPassed.add(PassedCompletedSongData(
            "https://upload.wikimedia.org/wikipedia/ko/thumb/f/fe/방탄소년단_MAP_OF_THE_SOUL_-_PERSONA.png/220px-방탄소년단_MAP_OF_THE_SOUL_-_PERSONA.png","WAVE","이성은"))
        dataListPassed.add(PassedCompletedSongData(
            "https://upload.wikimedia.org/wikipedia/ko/thumb/f/fe/방탄소년단_MAP_OF_THE_SOUL_-_PERSONA.png/220px-방탄소년단_MAP_OF_THE_SOUL_-_PERSONA.png","WAVE","이성은"))

        scoringPassedRecyclerViewAdapter = ScoringPassedRecyclerViewAdapter(context!!, dataListPassed)
        rv_scoring_song_passed.adapter = scoringPassedRecyclerViewAdapter
        rv_scoring_song_passed.layoutManager = LinearLayoutManager(context!!,LinearLayoutManager.HORIZONTAL, false)

        var dataListPassing: ArrayList<PassedSongData> = ArrayList()
        dataListPassing.add(PassedSongData(
            "https://upload.wikimedia.org/wikipedia/ko/thumb/f/fe/방탄소년단_MAP_OF_THE_SOUL_-_PERSONA.png/220px-방탄소년단_MAP_OF_THE_SOUL_-_PERSONA.png","WAVE","이성은","떵으니"))
        dataListPassing.add(PassedSongData(
            "https://upload.wikimedia.org/wikipedia/ko/thumb/f/fe/방탄소년단_MAP_OF_THE_SOUL_-_PERSONA.png/220px-방탄소년단_MAP_OF_THE_SOUL_-_PERSONA.png","WAVE","이성은","떵으니"))
        dataListPassing.add(PassedSongData(
            "https://upload.wikimedia.org/wikipedia/ko/thumb/f/fe/방탄소년단_MAP_OF_THE_SOUL_-_PERSONA.png/220px-방탄소년단_MAP_OF_THE_SOUL_-_PERSONA.png","WAVE","이성은","떵으니"))
        dataListPassing.add(PassedSongData(
            "https://upload.wikimedia.org/wikipedia/ko/thumb/f/fe/방탄소년단_MAP_OF_THE_SOUL_-_PERSONA.png/220px-방탄소년단_MAP_OF_THE_SOUL_-_PERSONA.png","WAVE","이성은","떵으니"))
        dataListPassing.add(PassedSongData(
            "https://upload.wikimedia.org/wikipedia/ko/thumb/f/fe/방탄소년단_MAP_OF_THE_SOUL_-_PERSONA.png/220px-방탄소년단_MAP_OF_THE_SOUL_-_PERSONA.png","WAVE","이성은","떵으니"))
        dataListPassing.add(PassedSongData(
            "https://upload.wikimedia.org/wikipedia/ko/thumb/f/fe/방탄소년단_MAP_OF_THE_SOUL_-_PERSONA.png/220px-방탄소년단_MAP_OF_THE_SOUL_-_PERSONA.png","WAVE","이성은","떵으니"))

        scoringPassingRecyclerViewAdapter = ScoringPassingRecyclerViewAdapter(context!!, dataListPassing)
        rv_scoring_song_passing.adapter = scoringPassingRecyclerViewAdapter
        rv_scoring_song_passing.layoutManager = LinearLayoutManager(context!!,LinearLayoutManager.HORIZONTAL, false)

        var dataListFailed: ArrayList<PassedSongData> = ArrayList()
        dataListFailed.add(PassedSongData(
            "https://upload.wikimedia.org/wikipedia/ko/thumb/f/fe/방탄소년단_MAP_OF_THE_SOUL_-_PERSONA.png/220px-방탄소년단_MAP_OF_THE_SOUL_-_PERSONA.png","WAVE","이성은","떵으니"))
        dataListFailed.add(PassedSongData(
            "https://upload.wikimedia.org/wikipedia/ko/thumb/f/fe/방탄소년단_MAP_OF_THE_SOUL_-_PERSONA.png/220px-방탄소년단_MAP_OF_THE_SOUL_-_PERSONA.png","WAVE","이성은","떵으니"))
        dataListFailed.add(PassedSongData(
            "https://upload.wikimedia.org/wikipedia/ko/thumb/f/fe/방탄소년단_MAP_OF_THE_SOUL_-_PERSONA.png/220px-방탄소년단_MAP_OF_THE_SOUL_-_PERSONA.png","WAVE","이성은","떵으니"))
        dataListFailed.add(PassedSongData(
            "https://upload.wikimedia.org/wikipedia/ko/thumb/f/fe/방탄소년단_MAP_OF_THE_SOUL_-_PERSONA.png/220px-방탄소년단_MAP_OF_THE_SOUL_-_PERSONA.png","WAVE","이성은","떵으니"))
        dataListFailed.add(PassedSongData(
            "https://upload.wikimedia.org/wikipedia/ko/thumb/f/fe/방탄소년단_MAP_OF_THE_SOUL_-_PERSONA.png/220px-방탄소년단_MAP_OF_THE_SOUL_-_PERSONA.png","WAVE","이성은","떵으니"))
        dataListFailed.add(PassedSongData(
            "https://upload.wikimedia.org/wikipedia/ko/thumb/f/fe/방탄소년단_MAP_OF_THE_SOUL_-_PERSONA.png/220px-방탄소년단_MAP_OF_THE_SOUL_-_PERSONA.png","WAVE","이성은","떵으니"))


        scoringFailedRecyclerViewAdapter = ScoringFailedRecyclerViewAdapter(context!!, dataListFailed)
        rv_scoring_song_failed.adapter = scoringFailedRecyclerViewAdapter
        rv_scoring_song_failed.layoutManager = LinearLayoutManager(context!!,LinearLayoutManager.HORIZONTAL, false)


    }

}